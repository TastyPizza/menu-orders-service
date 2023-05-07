package com.tastypizza.menuorders.services

import com.tastypizza.menuorders.entities.*
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.exceptions.IngredientsOutException
import com.tastypizza.menuorders.exceptions.ResourceNotFoundException
import com.tastypizza.menuorders.repositories.*
import com.tastypizza.menuorders.requests.MakeOrderRequest
import com.tastypizza.menuorders.util.CountsObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import javax.transaction.Transactional

@Service
class OrderService {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var menuItemOptionRepository: MenuItemOptionRepository

    @Autowired
    private lateinit var orderItemRepository: OrderItemRepository


    @Autowired
    private lateinit var ingredientsMenuItemsRepository: IngredientsMenuItemsRepository

    fun currentOrders(user: User): List<Order> {
        return orderRepository.findAllByClientIdAndStatusNot(user.id, OrderStatus.GIVEN)
    }

    fun currentOrdersInRestaurant(restaurantId: Long): List<Order> {
        return orderRepository.findAllByRestaurantIdAndStatusNot(restaurantId, OrderStatus.GIVEN)
    }

    fun check(menuItemId: Long, restaurantId: Long): Boolean {
        val listOfCounts: List<CountsObject> =
            ingredientsMenuItemsRepository.checkForIngredients(restaurantId, menuItemId)
                .orElseThrow { ResourceNotFoundException("Запрашиваемый ресурс не был найден!") }

        for (elem in listOfCounts)
            if (elem.menuItemCount > elem.restaurantCount)
                throw IngredientsOutException("Заказ не может быть выполнен из-за отсутствия необходимых ингредиентов")

        return true
    }

    @Transactional
    fun changeStatusOrder(user: User, orderId: Long, orderStatus: OrderStatus): Order {
        val order = orderRepository.findById(orderId).get()
        order.status = orderStatus
        orderRepository.save(order)
        return order
    }

    @Transactional
    fun order(makeOrderRequest: MakeOrderRequest): Boolean {
        if (!check()) return false

        val order = Order()
        order.clientId = makeOrderRequest.clientId
        order.orderDate = makeOrderRequest.orderDate
        order.packing = makeOrderRequest.packing
        order.status = makeOrderRequest.status
        orderRepository.save(order)

        for (orderItemDto in makeOrderRequest.listOfOrderItemDto!!) {
            val menuItemOption = menuItemOptionRepository.findById(orderItemDto.menuItemOptionId).get()

            val orderItem = OrderItem()
            orderItem.order = order
            orderItem.menuItemOption = menuItemOption
            orderItem.count = orderItemDto.count
            orderItemRepository.save(orderItem)
        }
        return true
    }


}