package com.funproject.developer.funproject.dto.transformer.userTransformer

import com.funproject.developer.funproject.dto.userDto.UserAddDto
import com.funproject.developer.funproject.model.User
import org.springframework.stereotype.Component

@Component
class UserAddTransformer {

    fun makeModel(userAddDto: UserAddDto): User {
        val user = User()
        user.setUsername(userAddDto.getUsername())
        user.setPassword(userAddDto.getPassword())
        user.setEmail(userAddDto.getEmail())
        return user
    }
}
