package com.tastypizza.menuorders.controllers

import com.tastypizza.menuorders.dto.OrderStatusesDTO
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.services.OrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/restaurant")
class RestaurantController (
    private val orderService: OrderService
){
    @GetMapping("/{restaurantId}")
    fun getOrdersHTML(@PathVariable restaurantId: Long, model: Model): String {
        val orders = orderService.todayOrders(restaurantId)
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        
        model.set("restaurantId", restaurantId)
        model.set("today", today)
        model.set("orders", orders)
        model.set("statuses", OrderStatus.values().map {
            OrderStatus.values().map { OrderStatusesDTO(it.id, it.name) }
        })
        
        return "orders"
    }
}