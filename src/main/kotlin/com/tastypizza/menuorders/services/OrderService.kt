package com.tastypizza.menuorders.services

import com.tastypizza.menuorders.entities.*
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.repositories.*
import com.tastypizza.menuorders.requests.MakeOrderRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
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
    private lateinit var ingredientsMenuItemOptionsRepository: IngredientsMenuItemOptionsRepository

    @Autowired
    private lateinit var restaurantsIngredientsRepository: RestaurantsIngredientsRepository

    fun currentOrders(user: User): List<Order> {
        return orderRepository.findAllByClientIdAndStatusNot(user.id, OrderStatus.GIVEN)
    }

    fun currentOrdersInRestaurant(restaurantId: Long): List<Order> {
        return orderRepository.findAllByRestaurantIdAndStatusNot(restaurantId, OrderStatus.GIVEN)
    }

    fun check(menuItemOptionId: Long?, restaurantId: Long?): Boolean {
        val ingredientsMenuItemOptions: List<IngredientsMenuItemOptions> =
            ingredientsMenuItemOptionsRepository.getAllByMenuItemOption(listOf(menuItemOptionId!!))

        val restaurantIngredients: List<RestaurantIngredients> =
            restaurantsIngredientsRepository.getAllByRestaurantId(restaurantId!!)

        for (ingredientsMenuItem in ingredientsMenuItemOptions) {

            for (restaurantIngredient in restaurantIngredients) {

                if (ingredientsMenuItem.ingredient == restaurantIngredient.ingredient) {
                    if (restaurantIngredient.count < ingredientsMenuItem.count) return false;
                }
            }
        }


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

        val order = Order()
        order.clientId = makeOrderRequest.clientId
        order.orderDate = makeOrderRequest.orderDate
        order.packing = makeOrderRequest.packing
        order.status = makeOrderRequest.status
        orderRepository.save(order)

        for (orderItemDto in makeOrderRequest.listOfOrderItemDto!!) {
            val menuItemOption = menuItemOptionRepository.findById(orderItemDto.menuItemOptionId).get()
            if (!check(menuItemOption.id, makeOrderRequest.restaurantId)) return false;

            val orderItem = OrderItem()
            orderItem.order = order
            orderItem.menuItemOption = menuItemOption
            orderItem.count = orderItemDto.count
            orderItemRepository.save(orderItem)
        }
        return true
    }


}