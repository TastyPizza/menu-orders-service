package com.tastypizza.menuorders

import com.tastypizza.menuorders.entities.Order
import com.tastypizza.menuorders.repositories.*
import com.tastypizza.menuorders.services.OrderService
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith

import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class OrderTestJotkin {

    @Mock
    private val orderRepository: OrderRepository? = null

    @Mock
    private val menuItemOptionRepository: MenuItemOptionRepository? = null

    @Mock
    private val orderItemRepository: OrderItemRepository? = null

    @Mock
    private val ingredientsMenuItemOptionsRepository: IngredientsMenuItemOptionsRepository? = null

    @Mock
    private val restaurantsIngredientsRepository: RestaurantsIngredientsRepository? = null

    @Mock
    private val restaurantsRepository: RestaurantsRepository? = null

    @InjectMocks
    private val orderService: OrderService? = null


    @Test
    fun todayOrdersTest() {
        val order1 = Order()
        order1.orderDate = LocalDateTime.now()
        order1.packing = true
        val order2 = Order()
        order2.orderDate = LocalDateTime.now()
        order2.packing = false
        val startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0)
        val endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59)
        val orderList: MutableList<Order> = ArrayList()
        orderList.add(order1)
        orderList.add(order2)
        Mockito.`when`<List<Order>>(orderRepository!!.findAllByOrderDateBetween(startDate, endDate))
            .thenReturn(orderList)
        val orderListFromService = orderService!!.todayOrders(1)
        Assertions.assertFalse(orderListFromService.isEmpty())
    }
}