package com.funproject.developer.funproject.dto.transformer.userTransformer

import com.funproject.developer.funproject.dto.userDto.ContributorDto
import com.funproject.developer.funproject.model.User
import org.springframework.stereotype.Component

@Component
class ContributorsListTransformer {

    fun makeDto(user: User): ContributorDto {
        return ContributorDto(id = user.id, firstname = user.firstname, lastname = user.lastname)
    }
}
