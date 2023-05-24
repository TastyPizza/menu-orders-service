package com.tastypizza.menuorders.controllers

import com.tastypizza.menuorders.dto.OrderStatusesDTO
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

    @GetMapping("check-menu-item")
    @Operation(summary = "Проверить есть ли на складе ресторана определенные menuItemOption")
    fun checkMenuItem(@RequestParam menuItemOptionId: Long, @RequestParam restaurantId: Long): Boolean {
        return orderService.check(listOf(menuItemOptionId), restaurantId)
    }

    @GetMapping("/currentOrdersInRestaurant")
    @Operation(summary = "Вернуть все заказы в ресторане кроме тех, что со статусом GIVEN")
    fun getCurrentOrdersInRestaurant(restaurantId: Long): List<Order> {
        return orderService.currentOrdersInRestaurant(restaurantId)
    }

    @PatchMapping("/changeStatus")
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
    fun todayOrders(@RequestParam restaurantId: Long): List<Order> {
        return orderService.todayOrders(restaurantId)
    }


    @GetMapping("/statuses")
    @Operation(summary = "Получить все статусы")
    fun getStatuses(): List<OrderStatusesDTO> {
        return OrderStatus.values().map { OrderStatusesDTO(it.id, it.name) }
    }


}