package com.tastypizza.menuorders.services

import com.tastypizza.menuorders.entities.MenuItem
import com.tastypizza.menuorders.entities.Order
import com.tastypizza.menuorders.entities.User
import com.tastypizza.menuorders.enums.OrderStatus
import com.tastypizza.menuorders.repositories.MenuItemRepository
import com.tastypizza.menuorders.repositories.OrderRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class OrderService {
    @Autowired
    private lateinit var orderRepository: OrderRepository

    fun currentOrders(user: User): List<Order> {
        return orderRepository.findAllByClientIdAndStatusNot(user.id, OrderStatus.GIVEN)
    }

    fun currentOrdersInRestaurant(restaurantId: Long): List<Order> {
        return orderRepository.findAllByRestaurantIdAndStatusNot(restaurantId, OrderStatus.GIVEN)
    }

    @Transactional
    fun changeStatusOrder(user: User, orderId: Long, orderStatus: OrderStatus): Order {
        val order = orderRepository.findById(orderId).get()
        order.status = orderStatus
        orderRepository.save(order)
        return order
    }
}