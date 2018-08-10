package com.funproject.developer.funproject.dto.loginDto

class LoginRequestDto {
    private val username: String? = null
    private val password: String? = null

    fun getUsername(): String? {
        return username
    }

    fun getPassword(): String? {
        return password
    }
}