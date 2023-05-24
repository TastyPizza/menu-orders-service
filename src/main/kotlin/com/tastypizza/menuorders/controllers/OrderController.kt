package com.tastypizza.menuorders.controllers

import com.tastypizza.menuorders.entities.Order
import com.tastypizza.menuorders.entities.User
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.requests.MakeOrderRequest
import com.tastypizza.menuorders.services.MenuItemService
import com.tastypizza.menuorders.services.OrderService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")

class OrderController() {

    @Autowired
    private lateinit var orderService: OrderService

    @GetMapping("check-menu-item/{menuItemOptionId}/{restaurantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Проверить есть ли на складе ресторана определенные menuItemOption")
    fun checkMenuItem(@PathVariable menuItemOptionId: Long, @PathVariable restaurantId: Long): Boolean {
        return orderService.check(menuItemOptionId, restaurantId)
    }

    @GetMapping("/currentOrdersInRestaurant")
    @Operation(summary = "Вернуть все заказы в ресторане кроме тех, что со статусом GIVEN")
    fun getCurrentOrdersInRestaurant(restaurantId: Long): List<Order> {
        return orderService.currentOrdersInRestaurant(restaurantId)
    }

    @PostMapping("/changeStatus")
    @Operation(summary = "Изменить статус заказа")
    fun changeOrderStatus(@RequestParam orderId: Long, @RequestParam statusId: Long): Order {
        return orderService.changeStatusOrder(orderId, statusId)
    }

    @PostMapping()
    @Operation(summary = "Сделать заказ")
    fun order(@RequestBody makeOrderRequest: MakeOrderRequest): Boolean {
        print(makeOrderRequest.clientId)
        print(makeOrderRequest.toString())
        return orderService.order(makeOrderRequest)

    }

    @GetMapping("/todayOrders")
    @Operation(summary = "Вернуть все заказы из ресторана за сегодня")
    fun todayOrders(@RequestParam restaurantId: Long): List<Order>{
        return orderService.todayOrders(restaurantId)
    }


    @GetMapping("/statuses")
    fun getStatuses(): List<OrderStatusResponse> {
        return OrderStatus.values().map { OrderStatusResponse(it.id, it.name) }
    }

    data class OrderStatusResponse(val id: Long, val status: String)


    @PatchMapping("/update-status/{orderId}/{statusId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateStatus(@RequestParam orderId: Long, @RequestParam statusId: Long) {
        orderService.updateOrderStatus(orderId, statusId)

    }
}