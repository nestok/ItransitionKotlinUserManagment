package com.funproject.developer.funproject.dto.transformer.userTransformer

import com.funproject.developer.funproject.dto.userDto.UserListDto
import com.funproject.developer.funproject.model.User
import org.springframework.stereotype.Component

@Component
class UserListTransformer {

    fun makeDto(user: User): UserListDto {
        return UserListDto(id = user.id, username = user.username, email = user.email, role = user.role.toString())
    }
}
