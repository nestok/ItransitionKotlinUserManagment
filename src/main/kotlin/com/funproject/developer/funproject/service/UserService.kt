package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.dto.transformer.userTransformer.ContributorsListTransformer
import com.funproject.developer.funproject.dto.transformer.userTransformer.UserAddTransformer
import com.funproject.developer.funproject.dto.transformer.userTransformer.UserListTransformer
import com.funproject.developer.funproject.dto.userDto.ContributorsListDto
import com.funproject.developer.funproject.dto.userDto.UserAddDto
import com.funproject.developer.funproject.dto.userDto.UserListDto
import com.funproject.developer.funproject.model.exception.EmailNotUniqueException
import com.funproject.developer.funproject.model.exception.UsernameNotUniqueException
import com.funproject.developer.funproject.model.User
import com.funproject.developer.funproject.model.UserRole
import com.funproject.developer.funproject.model.exception.AdminDeleteAttemptException
import com.funproject.developer.funproject.model.exception.UserNotFoundException
import com.funproject.developer.funproject.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Service
@Transactional
class UserService @Autowired constructor(
        private var userRepository: UserRepository,
        private var userAddTransformer: UserAddTransformer,
        private var userListTransformer: UserListTransformer,
        private var contributorsListTransformer: ContributorsListTransformer,
        private var authenticationService: AuthenticationService
) {

    fun register(userAddDto: UserAddDto) {
        val user = userAddTransformer.makeModel(userAddDto)
        if (this.isUsernameExists(user.username)) {
            throw UsernameNotUniqueException("User " + user.username + " is already exists")
        }
        if (this.isEmailExists(user.email)) {
            throw EmailNotUniqueException("Email " + user.email + " is already exists")
        }
        setDefaultSettings(user)
        userRepository.save(user)
    }

    fun findAllContributors(): ArrayList<ContributorsListDto> {
        val currentUser = authenticationService.getCurrentUser() ?: throw UserNotFoundException("User not found")
        val users = userRepository.findAllExisted()
        if (currentUser.role == UserRole.ROLE_USER)
            users.remove(currentUser)
        val contributorDtoList = ArrayList<ContributorsListDto>()
        for (user in users) {
            val dto = contributorsListTransformer.makeDto(user)
            contributorDtoList.add(dto)
        }
        return contributorDtoList
    }

    fun findAllUsers(): ArrayList<UserListDto> {
        val users = userRepository.findAllExisted()
        val userDtoList = ArrayList<UserListDto>()
        for (user in users) {
            val dto = userListTransformer.makeDto(user)
            userDtoList.add(dto)
        }
        return userDtoList
    }

    private fun isEmailExists(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }

    fun deleteUser(id: Long) {
        val deletedUser: User = userRepository.findById(id).orElse(null)
                ?: throw UserNotFoundException("User " + id + " not found")
        if (deletedUser.role == UserRole.ROLE_ADMIN)
            throw AdminDeleteAttemptException("Admin can't be deleted")
        deletedUser.is_deleted = true
        userRepository.save(deletedUser)
    }

    private fun encoder(user: User) {
        val encoder = BCryptPasswordEncoder()
        user.password = encoder.encode(user.password)
    }

    fun isUsernameExists(username: String): Boolean {
        return userRepository.findByUsername(username) != null
    }

    private fun setDefaultSettings(user: User) {
        encoder(user)
    }

}