package com.funproject.developer.funproject.dto.loginDto

import com.funproject.developer.funproject.model.User

class LoginResponseDto(private val token: String?, user: User?) {
    private var userId: Long = 0
    private var username: String = ""
    private var userRole: String = ""

    init {
        if (user != null) {
            this.userId = user.getId()!!
            this.username = user.getUsername()
            this.userRole = user.getRole().toString()
        }
    }
}
