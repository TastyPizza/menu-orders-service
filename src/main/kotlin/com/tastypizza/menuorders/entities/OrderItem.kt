package com.tastypizza.menuorders.entities

import lombok.Getter
import lombok.Setter
import javax.persistence.*

@Entity
@Getter
@Setter
data class OrderItem (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?=0,
    val menuItemId: Int?=0,
    val count: Int?=0,
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    val order: Order?=null
)