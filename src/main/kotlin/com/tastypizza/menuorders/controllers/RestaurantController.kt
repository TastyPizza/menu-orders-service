package com.tastypizza.menuorders.controllers

import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.services.OrderService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Controller
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
            OrderController.OrderStatusResponse(it.id, it.name) 
        })
        
        return "orders"
    }
}