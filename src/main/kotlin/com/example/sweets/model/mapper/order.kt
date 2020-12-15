package com.example.sweets.model.mapper

import com.example.sweets.model.storage.Order
import com.example.sweets.repository.ClientRepository
import com.example.sweets.repository.OperatorRepository
import com.example.sweets.repository.ChefrRepository
import com.example.sweets.model.api.Order as ApiOrder

fun Order.toApi() = ApiOrder(
    id = id,
    status = status,
    products = products,
    operatorId = operator?.id,
    clientId = client.id,
    chefId = chef?.id
)

fun ApiOrder.toDomain(chefs: ChefrRepository, clients: ClientRepository, operators: OperatorRepository) = Order(
    id = id,
    status = status,
    products = products,
    operator = operatorId?.let { operators.findById(it).get() },
    client = clientId.let { clients.findById(it).get() },
    chef = chefId?.let { chefs.findById(it).get() }
)