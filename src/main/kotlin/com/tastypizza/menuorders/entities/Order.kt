package com.tastypizza.menuorders.entities

import com.tastypizza.menuorders.enums.OrderStatus
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Data
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
data class Order (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?= null,


    var clientId: Long?=0,
    var restaurantId: Long?=0,
    var orderDate: LocalDateTime?= LocalDateTime.now(),
    var status: OrderStatus?=OrderStatus.NEW,
    var packing: Boolean?=false,


    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    var orderItems: Set<OrderItem> = emptySet()



) {
    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}