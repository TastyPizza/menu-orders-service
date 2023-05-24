package com.tastypizza.menuorders.dto

import java.io.Serializable


class OrderItemDTO (
    val menuItemOptionId: Long,
    val count: Int
) : Serializable