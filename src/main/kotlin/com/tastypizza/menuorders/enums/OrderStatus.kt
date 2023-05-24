package com.tastypizza.menuorders.enums

enum class OrderStatus(val id: Long) {
    NEW(0),
    IN_PROGRESS(1),
    READY(2),
    GIVEN(3),
    CANCELED(4)
}