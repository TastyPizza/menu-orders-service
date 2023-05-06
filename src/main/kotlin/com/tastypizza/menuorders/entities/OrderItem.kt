package com.tastypizza.menuorders.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "order_item")
data class OrderItem(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long ?= null,

    @Column(name = "count")
    var count: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    var order: Order? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    var menuItemOption: MenuItemOption? = null

)