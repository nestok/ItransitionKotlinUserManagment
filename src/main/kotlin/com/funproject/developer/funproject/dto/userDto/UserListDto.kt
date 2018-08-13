package com.funproject.developer.funproject.dto.userDto

import com.funproject.developer.funproject.model.UserRole

class UserListDto (
        val id: Long = -1,
        val username: String = "",
        val email: String = "",
        val role: String = UserRole.ROLE_USER.toString()
) {

}