package com.tastypizza.menuorders.dto

data class OrderDTO (
    var id: Long?= null,
    val date: String?= "",
    val status: String?= "",
    val packing: String?= "НЕТ",
)