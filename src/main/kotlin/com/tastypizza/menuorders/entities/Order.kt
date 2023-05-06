package com.tastypizza.menuorders.entities

import com.tastypizza.menuorders.enums.OrderStatus
import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Getter
@Setter
@Table(name="orders")
data class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = 0,


    val clientId: Int?=0,
    val restaurantId: Int?=0,
    val orderDate: String?="",
    var status: OrderStatus?=OrderStatus.NEW,
    val packing: Boolean?=false,

    @OneToMany(mappedBy = "order")
    val orderItems: Set<OrderItem> = emptySet()

)