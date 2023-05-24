package com.tastypizza.menuorders.entities


import com.fasterxml.jackson.annotation.JsonIgnore
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    var order: Order? = null,


    @ManyToOne(fetch = FetchType.LAZY)
    var menuItemOption: MenuItemOption? = null

) {
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}