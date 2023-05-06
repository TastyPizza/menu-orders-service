package com.tastypizza.menuorders.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "order_item")
data class OrderItem(

    @EmbeddedId
    val id: OrderItemId = OrderItemId(),

    @Column(name = "count")
    var count: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    val order: Order? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuItemOptionId")
    val menuItemOption: MenuItemOption? = null

)

@Embeddable
data class OrderItemId(

    @Column(name = "order_id")
    val orderId: Long? = null,

    @Column(name = "menu_item_option_id")
    val menuItemOptionId: Long? = null

) : Serializable