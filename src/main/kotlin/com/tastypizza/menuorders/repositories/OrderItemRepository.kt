package com.tastypizza.menuorders.repositories


import com.tastypizza.menuorders.entities.OrderItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderItemRepository : JpaRepository<OrderItem, Long> {
}