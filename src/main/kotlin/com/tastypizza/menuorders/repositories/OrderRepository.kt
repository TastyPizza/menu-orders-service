package com.tastypizza.menuorders.repositories

import com.tastypizza.menuorders.entities.Order
import com.tastypizza.menuorders.enums.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface OrderRepository : JpaRepository<Order, Long> {
    fun findAllByClientId(clientId: Long): MutableList<Order>
    fun findAllByClientIdAndStatusNot(clientId: Long, status: OrderStatus): MutableList<Order>
    fun findAllByRestaurantIdAndStatusNot(restaurantId: Long, status: OrderStatus): MutableList<Order>
    fun findAllByOrderDateBetween(startDate: LocalDateTime, endDate: LocalDateTime): MutableList<Order>

}