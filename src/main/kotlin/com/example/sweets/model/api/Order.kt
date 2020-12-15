package com.example.sweets.model.api

import com.example.sweets.model.storage.OrderStatus

data class Order(
    val id: Long,
    val products: ArrayList<String> = arrayListOf(),
    var status: OrderStatus,
    val operatorId: Long? = null,
    val clientId: Long,
    var chefId: Long? = null
)