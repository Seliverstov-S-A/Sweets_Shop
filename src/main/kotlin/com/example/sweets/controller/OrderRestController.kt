package com.example.sweets.controller

import com.example.sweets.model.mapper.toApi
import com.example.sweets.model.mapper.toDomain
import com.example.sweets.repository.*
import com.example.sweets.util.extension.ElementAlreadyExists
import com.example.sweets.util.extension.ElementNotFound
import org.springframework.web.bind.annotation.*
import com.example.sweets.model.api.Order as ApiRequest

@RestController
@RequestMapping("/api/orders")
class OrderRestController(
        private val orders: OrderRepository,
        private val chefs: ChefrRepository,
        private val clients: ClientRepository,
        private val operators: OperatorRepository
) {

    @GetMapping("/all")
    fun all() = orders.findAll().map { it.toApi() }

    @PostMapping("/create")
    fun create(@RequestBody request: ApiRequest): Any = when {
        orders.existsById(request.id) -> ElementAlreadyExists()
        else -> orders.save(request.toDomain(chefs, clients, operators)).toApi()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id") id: Long): Any = try {
        orders.findById(id).get().toApi()
    } catch (e: Exception) {
        ElementNotFound()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long) = try {
        orders.deleteById(id)
    } catch (e: Exception) {
        ElementNotFound()
    }

    @PostMapping("/change")
    fun update(@RequestBody request: ApiRequest): Any = when {
        orders.existsById(request.id) -> {
            val entity = orders.findById(request.id).get()
            entity.chef = request.chefId?.let { chefs.findById(it).get() }
            entity.status = request.status
            entity.products = request.products
            orders.save(entity)
        }
        else -> ElementNotFound()
    }
}