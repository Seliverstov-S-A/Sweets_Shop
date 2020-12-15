package com.example.sweets

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SweetsApplication

fun main(args: Array<String>) {
    runApplication<SweetsApplication>(*args)
}

