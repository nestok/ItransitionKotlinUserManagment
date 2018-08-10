package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.dto.errorDto.ErrorDto
import com.funproject.developer.funproject.dto.userDto.UserAddDto
import com.funproject.developer.funproject.model.User
import com.funproject.developer.funproject.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/users")
    fun findAllUsers(): Iterable<User> {
        return userService.findAllUsers()
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    fun register(@RequestBody user: UserAddDto) {
        userService.register(user)
    }

//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Cannot operate function")  // 404
//    inner class ErrorRegisterUserException : RuntimeException()

}