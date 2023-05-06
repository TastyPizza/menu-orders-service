package com.tastypizza.menuorders.dto

import com.tastypizza.menuorders.entities.MenuItemOption
import java.io.Serializable


class OrderItemDTO (
    val menuItemOptionId: Long,
    val count: Int
) : Serializable