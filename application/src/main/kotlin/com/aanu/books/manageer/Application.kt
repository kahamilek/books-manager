package com.aanu.books.manageer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.reactive.config.EnableWebFlux

@EnableScheduling
@EnableWebFlux
@EnableCaching
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}