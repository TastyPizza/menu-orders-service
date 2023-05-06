package com.tastypizza.menuorders.requests

import com.tastypizza.menuorders.enums.OrderStatus
import lombok.AllArgsConstructor
import lombok.Data
import java.io.Serializable
import java.time.LocalDateTime
import javax.validation.constraints.NotNull

@Data
@AllArgsConstructor
class MakeOrderRequest(
    private val clientId: Int,
    private val restaurantId: Int,
    private val orderDate: LocalDateTime?=LocalDateTime.now(),
    private val status: OrderStatus?=OrderStatus.NEW,
    private val packing: Boolean?=false,
    private val menuItemOptions: List<Integer>

    ) : Serializable