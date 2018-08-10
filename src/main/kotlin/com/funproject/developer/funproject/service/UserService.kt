package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.dto.transformer.userTransformer.UserAddTransformer
import com.funproject.developer.funproject.dto.userDto.UserAddDto
import com.funproject.developer.funproject.model.exceptions.EmailNotUniqueException
import com.funproject.developer.funproject.model.exceptions.UsernameNotUniqueException
import com.funproject.developer.funproject.model.User
import com.funproject.developer.funproject.model.UserRole
import com.funproject.developer.funproject.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Service
@Transactional
class UserService @Autowired constructor(
        private var userRepository: UserRepository,
        private var userAddTransformer: UserAddTransformer
) {

    fun register(userAddDto: UserAddDto) {
        val user = userAddTransformer.makeModel(userAddDto)
        if (this.isUsernameExists(user.getUsername())) {
            throw UsernameNotUniqueException("User " + user.getUsername() + " is already exists")
        }
        if (this.isEmailExsists(user.getEmail())) {
            throw EmailNotUniqueException("Email " + user.getEmail() + " is already exists")
        }
        setDefaultSettings(user)
        userRepository.save(user)
    }

    fun findAllUsers(): Iterable<User> {
        return userRepository.findAll()
    }

    private fun isEmailExsists(email: String): Boolean {
        return userRepository.findByEmail(email) != null
    }

//    private fun newActivationCode(user: User) {
//        user.setActivationCode(UUID.randomUUID().toString())
//    }

    private fun encoder(user: User) {
        val encoder = BCryptPasswordEncoder()
        user.setPassword(encoder.encode(user.getPassword()))
    }

    fun isUsernameExists(username: String): Boolean {
        return userRepository.findByUsername(username) != null
    }

    private fun setDefaultSettings(user: User) {
        encoder(user)
//        newActivationCode(user)
        user.setRole(UserRole.ROLE_USER)
    }

}