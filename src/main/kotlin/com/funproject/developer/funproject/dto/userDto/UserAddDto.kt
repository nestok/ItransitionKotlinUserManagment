package com.funproject.developer.funproject.dto.userDto

class UserAddDto {

    private val username: String = ""
    private val password: String = ""
    private val email: String = ""

    fun getUsername(): String {
        return username
    }

    fun getPassword(): String {
        return password
    }

    fun getEmail(): String {
        return email
    }
}