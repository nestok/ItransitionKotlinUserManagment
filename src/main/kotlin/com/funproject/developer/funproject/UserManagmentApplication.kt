package com.funproject.developer.funproject

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
//@EnableEurekaServer
class UserManagmentApplication

fun main(args: Array<String>) {
    runApplication<UserManagmentApplication>(*args)
}

