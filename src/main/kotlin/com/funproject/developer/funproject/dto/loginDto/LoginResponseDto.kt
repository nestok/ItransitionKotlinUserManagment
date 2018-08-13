package com.funproject.developer.funproject.dto.loginDto

import com.funproject.developer.funproject.model.User

class LoginResponseDto(val token: String?) {
    var userId: Long = 0
    var username: String = ""
    var userRole: String = ""

    constructor(token: String?, user: User?) : this(token){
        if (user != null) {
            this.userId = user.id
            this.username = user.username
            this.userRole = user.role.toString()
        }
    }

}
