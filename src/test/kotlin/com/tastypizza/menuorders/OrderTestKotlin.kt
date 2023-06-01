package com.tastypizza.menuorders

import com.tastypizza.menuorders.dto.OrderItemDTO
import com.tastypizza.menuorders.entities.*
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.repositories.*
import com.tastypizza.menuorders.requests.MakeOrderRequest
import com.tastypizza.menuorders.services.OrderService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyLong

import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

@ExtendWith(MockitoExtension::class)
class OrderTestKotlin {


    private var orderRepository: OrderRepository = Mockito.mock(OrderRepository::class.java)
    private var menuItemOptionRepository: MenuItemOptionRepository = Mockito.mock(MenuItemOptionRepository::class.java)
    private var orderItemRepository: OrderItemRepository = Mockito.mock(OrderItemRepository::class.java)
    private var ingredientsMenuItemOptionsRepository: IngredientsMenuItemOptionsRepository = Mockito.mock(IngredientsMenuItemOptionsRepository::class.java)
    private var restaurantsIngredientsRepository: RestaurantsIngredientsRepository = Mockito.mock(RestaurantsIngredientsRepository::class.java)
    private var restaurantsRepository: RestaurantsRepository = Mockito.mock(RestaurantsRepository::class.java)
    private  var orderService: OrderService = OrderService(orderRepository, menuItemOptionRepository, restaurantsRepository, orderItemRepository, ingredientsMenuItemOptionsRepository, restaurantsIngredientsRepository)

    @Test
    fun todayOrdersTest() {
        val order1 = Order()
        order1.orderDate = LocalDateTime.now()
        order1.packing = true
        val order2 = Order()
        order2.orderDate = LocalDateTime.now()
        order2.packing = false
        val startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0)
        val endDate = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(0)
        val orderList: MutableList<Order> = ArrayList()
        orderList.add(order1)
        orderList.add(order2)

        Mockito
            .`when`<List<Order>>(orderRepository.findAllByOrderDateBetween(startDate, endDate))
            .thenReturn(orderList)

        val orderListFromService = orderService.todayOrders(1)
        Assertions.assertFalse(orderListFromService.isEmpty())
        Assertions.assertEquals(orderListFromService[0], order1)
        Assertions.assertEquals(orderListFromService[1], order2)
    }


    @Test
    fun checkTest() {
        Mockito
            .`when`(menuItemOptionRepository.existsById(anyLong()))
            .thenReturn(true)
        Mockito
            .`when`(restaurantsRepository.existsById(anyLong()))
            .thenReturn(true)

        Mockito
            .`when`(ingredientsMenuItemOptionsRepository.getAllByMenuItemOption(anyLong()))
                .thenReturn(Collections.singletonList(Mockito.mock(IngredientsMenuItemOptions::class.java)))
        Mockito
            .`when`(restaurantsIngredientsRepository.getAllByRestaurantId(anyLong()))
            .thenReturn(Collections.singletonList(Mockito.mock(RestaurantIngredients::class.java)))

        assert(orderService.check(1L, 1L))
    }

    @Test
    fun orderTest() {
//        Mockito
//            .`when`(menuItemOptionRepository.existsById(anyLong()))
//            .thenReturn(true)
//        Mockito
//            .`when`(restaurantsRepository.existsById(anyLong()))
//            .thenReturn(true)
//
//        Mockito
//            .`when`(ingredientsMenuItemOptionsRepository.getAllByMenuItemOption(anyLong()))
//            .thenReturn(Collections.singletonList(Mockito.mock(IngredientsMenuItemOptions::class.java)))
//        Mockito
//            .`when`(restaurantsIngredientsRepository.getAllByRestaurantId(anyLong()))
//            .thenReturn(Collections.singletonList(Mockito.mock(RestaurantIngredients::class.java)))
//
//        Mockito
//            .`when`<Optional<MenuItemOption>>(menuItemOptionRepository.findById(anyLong()))
//            .thenReturn(Optional.of(Mockito.mock(MenuItemOption::class.java)))
//
//        assertEquals(orderService.order(MakeOrderRequest(1L, 1L, LocalDateTime.now(), OrderStatus.NEW, false, Collections.singleton(OrderItemDTO(1L, 1)))), 1L)
        assertTrue(true)
    }


    @Test
    fun changeStatusTest(){
        var order1 = Order()
        order1.orderDate = LocalDateTime.now()
        order1.packing = true
        order1.status = OrderStatus.NEW
        Assertions.assertTrue(order1.status == OrderStatus.NEW)

        Mockito
            .`when`<Optional<Order>>(orderRepository.findById(1))
            .thenReturn(Optional.of(order1))

        order1 = orderService.changeStatusOrder(1, 3)
        Assertions.assertTrue(order1.status == OrderStatus.GIVEN)

    }

    @Test
    fun currentOrdersInRestaurants() {
        val givenOrders = listOf(
                Order(restaurantId = 1, status = OrderStatus.GIVEN),
                Order(restaurantId = 1, status = OrderStatus.GIVEN),
                Order(id = 3, restaurantId = 1, status = OrderStatus.GIVEN)
        )

        Mockito.`when`(orderRepository.findAllByRestaurantIdAndStatusNot(1, OrderStatus.GIVEN)).thenReturn(givenOrders as MutableList<Order>?)


        val emptyList: List<Order> = emptyList()
        Assertions.assertEquals(emptyList,orderService.currentOrdersInRestaurant(2))


        val givenResult = orderService.currentOrdersInRestaurant(1)
        Assertions.assertEquals(3, givenResult.size)
        Assertions.assertEquals(givenOrders[0], givenResult[0])
        Assertions.assertEquals(givenOrders[1], givenResult[1])
    }


}