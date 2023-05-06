package com.tastypizza.menuorders.requests

import com.tastypizza.menuorders.dto.OrderItemDTO
import com.tastypizza.menuorders.enums.OrderStatus
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.io.Serializable
import java.time.LocalDateTime
import javax.validation.constraints.NotNull


data class MakeOrderRequest(
    val clientId: Int?=null,
    val restaurantId: Int?=null,
    val orderDate: LocalDateTime?=LocalDateTime.now(),
    val status: OrderStatus?=OrderStatus.NEW,
    val packing: Boolean?=false,
    val listOfOrderItemDto: Set<OrderItemDTO>?= emptySet()
    ) : Serializable