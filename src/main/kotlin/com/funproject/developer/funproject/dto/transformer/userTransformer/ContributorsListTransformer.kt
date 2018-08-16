package com.funproject.developer.funproject.dto.transformer.userTransformer

import com.funproject.developer.funproject.dto.userDto.ContributorsListDto
import com.funproject.developer.funproject.dto.userDto.UserListDto
import com.funproject.developer.funproject.model.User
import org.springframework.stereotype.Component

@Component
class ContributorsListTransformer {

    fun makeDto(user: User): ContributorsListDto {
        return ContributorsListDto(id = user.id, firstName = user.firstname, lastName = user.lastname)
    }
}
