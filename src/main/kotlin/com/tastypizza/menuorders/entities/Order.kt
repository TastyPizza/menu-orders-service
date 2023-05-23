package com.tastypizza.menuorders.entities

import com.tastypizza.menuorders.enums.OrderStatus
import lombok.AllArgsConstructor
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
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
    var restaurantId: Int?=0,
    var orderDate: LocalDateTime?= LocalDateTime.now(),
    var status: OrderStatus?=OrderStatus.NEW,
    var packing: Boolean?=false,

    @OneToMany(mappedBy = "order")
    var orderItems: Set<OrderItem> = emptySet()

)