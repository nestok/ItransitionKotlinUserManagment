package com.funproject.developer.funproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class UserManagmentApplication

fun main(args: Array<String>) {
    runApplication<UserManagmentApplication>(*args)
}

