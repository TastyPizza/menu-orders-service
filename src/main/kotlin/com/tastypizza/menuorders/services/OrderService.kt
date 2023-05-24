package com.tastypizza.menuorders.services

import com.tastypizza.menuorders.entities.*
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.exceptions.IngredientsOutException
import com.tastypizza.menuorders.repositories.*
import com.tastypizza.menuorders.requests.MakeOrderRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.awt.Menu
import java.time.LocalDateTime
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

    @Transactional
//  понял что сделал хуевый чек, ибо нужно учесть кол-во переданное в
//  OrderItem некита + проверяю ингредиенты итеративно, то-есть на
//  момент какой-либо итерации ингредиентов может
//  не быть т.к уйдут на другой меню итем опшин.
//  Фиксану завтра ща впадлу, спать буду

    fun check(menuItemOptionId: List<Long>?, restaurantId: Long?): Boolean {
        val ingredientsMenuItemOptions: List<IngredientsMenuItemOptions> =
            ingredientsMenuItemOptionsRepository.getAllByMenuItemOption(menuItemOptionId!!)

        val restaurantIngredients: List<RestaurantIngredients> =
            restaurantsIngredientsRepository.getAllByRestaurantId(restaurantId!!)

        for (ingredientsMenuItem in ingredientsMenuItemOptions) {

            for (restaurantIngredient in restaurantIngredients) {

                if (ingredientsMenuItem.ingredient == restaurantIngredient.ingredient) {
                    if (restaurantIngredient.count < ingredientsMenuItem.count) throw IngredientsOutException("Недостаточно ингредиентов для приготовления этого блюда")
                }
            }
        }

        return true
    }

    @Transactional
    fun changeStatusOrder(orderId: Long, statusId: Long): Order {
        val order = orderRepository.findById(orderId).get()
        order.status = OrderStatus.fromId(statusId)
        orderRepository.save(order)
        return order
    }

    @Transactional
    fun order(makeOrderRequest: MakeOrderRequest): Boolean {

        val menuItemOptions: MutableList<Long> = ArrayList()
        for (orderItemDto in makeOrderRequest.listOfOrderItemDto!!) {
            menuItemOptions.add(orderItemDto.menuItemOptionId)
        }

        check(menuItemOptions, makeOrderRequest.restaurantId)

        val order = Order()
        order.clientId = makeOrderRequest.clientId
        order.orderDate = makeOrderRequest.orderDate
        order.packing = makeOrderRequest.packing
        order.status = makeOrderRequest.status
        orderRepository.save(order)


        for (orderItemDto in makeOrderRequest.listOfOrderItemDto!!) {
            val menuItemOption = menuItemOptionRepository.findById(orderItemDto.menuItemOptionId).get()
//          если то что я написал в тг - верно, то нужно вместо
//          уменьшения каунта меню итем опшиона сделать уменьшение
//          ингредиентов используемых для этого меню итем
//          опшиона * на orderItemDto.count именно в рестике, куда идет заказ
            menuItemOption.count = menuItemOption.count - orderItemDto.count

            val orderItem = OrderItem()
            orderItem.order = order
            orderItem.menuItemOption = menuItemOption
            orderItem.count = orderItemDto.count
            orderItemRepository.save(orderItem)
        }
        return true
    }

    fun todayOrders(restaurantId: Long): List<Order> {
        val startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0)
        val endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59)
        return orderRepository.findByOrderDateBetween(startDate, endDate)
    }


}