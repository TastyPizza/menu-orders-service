package com.tastypizza.menuorders.controllers

import com.tastypizza.menuorders.entities.Order
import com.tastypizza.menuorders.entities.User
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.requests.MakeOrderRequest
import com.tastypizza.menuorders.services.MenuItemService
import com.tastypizza.menuorders.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController() {

    @Autowired
    private lateinit var orderService: OrderService

    @GetMapping("/currentOrders")
    fun getCurrentOrders(user: User): List<Order> {
        return orderService.currentOrders(user)
    }

    @GetMapping("check-menu-item/{menuItemOptionId}/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun checkMenuItem(@PathVariable menuItemOptionId: Long, @PathVariable restaurantId: Long) {
        orderService.check(menuItemOptionId, restaurantId)
    }

    @GetMapping("/currentOrdersInRestaurant")
    fun getCurrentOrdersInRestaurant(restaurantId: Long): List<Order> {
        return orderService.currentOrdersInRestaurant(restaurantId)
    }

    @PostMapping("/changeStatus")
    fun changeOrderStatus(user: User, orderId: Long, orderStatus: OrderStatus): Order {
        return orderService.changeStatusOrder(user, orderId, orderStatus)
    }

    @PostMapping("/order")
    fun order(@RequestBody makeOrderRequest: MakeOrderRequest): Boolean {
        print(makeOrderRequest.clientId)
        print(makeOrderRequest.toString())
        return orderService.order(makeOrderRequest)
    }

    @GetMapping("/statuses")
    fun getStatuses(user: User): List<OrderStatusResponse> {
        return OrderStatus.values().map { OrderStatusResponse(it.id, it.name) }
    }

    data class OrderStatusResponse(val id: Long, val status: String)


    @PatchMapping("/update-status/{orderId}/{statusId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateStatus(@PathVariable orderId: Long, @PathVariable statusId: Long) {
        orderService.updateOrderStatus(orderId, statusId)
    }

}