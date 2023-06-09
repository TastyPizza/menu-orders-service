package com.tastypizza.menuorders.services

import com.tastypizza.menuorders.entities.*
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.exceptions.IngredientsOutException
import com.tastypizza.menuorders.exceptions.ResourceNotFoundException
import com.tastypizza.menuorders.repositories.*
import com.tastypizza.menuorders.requests.MakeOrderRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional

@Service
class OrderService @Autowired constructor(private val orderRepository: OrderRepository, private val menuItemOptionRepository: MenuItemOptionRepository, private val restaurantsRepository: RestaurantsRepository,
    private val orderItemRepository: OrderItemRepository, private val ingredientsMenuItemOptionsRepository: IngredientsMenuItemOptionsRepository, private val restaurantsIngredientsRepository: RestaurantsIngredientsRepository) {
    fun currentOrders(user: User): List<Order> {
        return orderRepository.findAllByClientIdAndStatusNot(user.id, OrderStatus.GIVEN)
    }

    fun currentOrdersInRestaurant(restaurantId: Long): List<Order> {
        return orderRepository.findAllByRestaurantIdAndStatusNot(restaurantId, OrderStatus.GIVEN)
    }

    @Transactional
    fun check(menuItemOptionId: Long, restaurantId: Long): Boolean {
        if (!menuItemOptionRepository.existsById(menuItemOptionId)) throw ResourceNotFoundException("Опция с id = $menuItemOptionId не найдена")
        if (!restaurantsRepository.existsById(restaurantId)) throw ResourceNotFoundException("Ресторан с id = $restaurantId не найден")

        val ingredientsMenuItemOptions: List<IngredientsMenuItemOptions> =
                ingredientsMenuItemOptionsRepository.getAllByMenuItemOption(menuItemOptionId)

        val restaurantIngredients: List<RestaurantIngredients> =
                restaurantsIngredientsRepository.getAllByRestaurantId(restaurantId)

        for (ingredientsMenuItem in ingredientsMenuItemOptions) {
            for (restaurantIngredient in restaurantIngredients) {
                if ((ingredientsMenuItem.ingredient == restaurantIngredient.ingredient)
                        && (restaurantIngredient.count < ingredientsMenuItem.count)
                ) {
                    throw IngredientsOutException("Недостаточно ингредиентов для приготовления этого блюда")
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
    fun order(makeOrderRequest: MakeOrderRequest): Long {
        if (!restaurantsRepository.existsById(makeOrderRequest.restaurantId!!)) throw ResourceNotFoundException("Ресторан с id = ${makeOrderRequest.restaurantId} не найден")

        val order = Order()
        order.clientId = makeOrderRequest.clientId
        order.orderDate = makeOrderRequest.orderDate
        order.packing = makeOrderRequest.packing
        order.status = makeOrderRequest.status
        order.restaurantId = makeOrderRequest.restaurantId
        orderRepository.save(order)

        val restaurantIngredients: List<RestaurantIngredients> =
                restaurantsIngredientsRepository.getAllByRestaurantId(makeOrderRequest.restaurantId)

        for (orderItemDto in makeOrderRequest.listOfOrderItemDto!!) {
            val menuItemOption = menuItemOptionRepository.findByIdOrNull(orderItemDto.menuItemOptionId)
                    ?: throw ResourceNotFoundException("Опция ${orderItemDto.menuItemOptionId} не найдена!")

            val ingredientsMenuItemOptions: List<IngredientsMenuItemOptions> =
                    ingredientsMenuItemOptionsRepository.getAllByMenuItemOption(orderItemDto.menuItemOptionId)

            for (ingredientsMenuItem in ingredientsMenuItemOptions) {
                for (restaurantIngredient in restaurantIngredients) {
                    try {
                        if ((ingredientsMenuItem.ingredient.id == restaurantIngredient.ingredient.id)) {

                            if (restaurantIngredient.count < ingredientsMenuItem.count * orderItemDto.count)
                                throw IngredientsOutException("Недостаточно ингредиентов для приготовления блюда ${menuItemOption.menuItem!!.name}${menuItemOption.name}")
                            else
                                restaurantIngredient.count -= ingredientsMenuItem.count * orderItemDto.count

                        }
                    } catch (_: Exception) {

                    }
                }
            }

            val orderItem = OrderItem()
            orderItem.order = order
            orderItem.menuItemOption = menuItemOption
            orderItem.count = orderItemDto.count
            orderItemRepository.save(orderItem)
        }
        return order.id!!
    }

    fun todayOrders(restaurantId: Long): List<Order> {
        val startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        val endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(0)
        return orderRepository.findAllByOrderDateBetween(startDate, endDate)
    }


}