package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.dto.loginDto.LoginRequestDto
import com.funproject.developer.funproject.dto.loginDto.LoginResponseDto
import com.funproject.developer.funproject.model.User
import com.funproject.developer.funproject.service.AuthenticationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = arrayOf("http://localhost:4200"), maxAge = 3600)
@RequestMapping(value = "/auth", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
class AuthenticationController {

    @Autowired
    lateinit var authenticationService: AuthenticationService

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDto: LoginRequestDto): LoginResponseDto {
        return authenticationService.login(loginRequestDto)
    }

}