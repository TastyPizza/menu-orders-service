package com.tastypizza.menuorders.controllers

import com.tastypizza.menuorders.entities.MenuItem
import com.tastypizza.menuorders.entities.Order
import com.tastypizza.menuorders.entities.User
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.services.MenuItemService
import com.tastypizza.menuorders.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController() {

    @Autowired
    private lateinit var orderService: OrderService

    @GetMapping("/currentOrders")
    fun getCurrentOrders(user: User): List<Order> {
        return orderService.currentOrders(user)
    }

    @GetMapping("/currentOrdersInRestaurant")
    fun getCurrentOrdersInRestaurant(restaurantId: Long): List<Order> {
        return orderService.currentOrdersInRestaurant(restaurantId)
    }

    @PostMapping("/changeStatus")
    fun changeOrderStatus(user: User, orderId: Long, orderStatus: OrderStatus): Order {
        return orderService.changeStatusOrder(user, orderId, orderStatus)
    }
}