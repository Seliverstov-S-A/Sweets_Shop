package com.example.sweets.controller

import com.example.sweets.model.api.LoginAndPassword
import com.example.sweets.model.storage.Chef
import com.example.sweets.repository.ChefrRepository
import com.example.sweets.util.extension.ElementAlreadyExists
import com.example.sweets.util.extension.ElementNotFound
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chefs")
class ChefRestController(private val chefs: ChefrRepository) {

    @GetMapping("/all")
    fun all() = chefs.findAll()

    @PostMapping("/create")
    fun create(@RequestBody chef: Chef): Any = when {
        chefs.findAll().any { it.login == chef.login } -> ElementAlreadyExists()
        else -> chefs.save(chef)
    }

    @PostMapping("/sign")
    fun signIn(@RequestBody loginAndPassword: LoginAndPassword): Any {
        val (login, password) = loginAndPassword
        return chefs.findAll().find { it.login == login && it.password == password } ?: ElementNotFound()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable(value = "id") id: Long): Any = try {
        chefs.findById(id).get()
    } catch (e: Exception) {
        ElementNotFound()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable(value = "id") id: Long) = try {
        chefs.deleteById(id)
    } catch (e: Exception) {
        ElementNotFound()
    }
}