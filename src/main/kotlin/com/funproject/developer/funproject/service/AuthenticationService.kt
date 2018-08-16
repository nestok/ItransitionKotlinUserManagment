package com.funproject.developer.funproject.service

import com.funproject.developer.funproject.dto.loginDto.LoginRequestDto
import com.funproject.developer.funproject.dto.loginDto.LoginResponseDto
import com.funproject.developer.funproject.model.User
import com.funproject.developer.funproject.model.exception.AuthenticationFailedException
import com.funproject.developer.funproject.model.exception.UserNotFoundException
import com.funproject.developer.funproject.repository.UserRepository
import com.funproject.developer.funproject.security.model.JwtUserDetails
import com.funproject.developer.funproject.security.service.AuthenticationHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class AuthenticationService @Autowired constructor(
        private val authenticationHelper: AuthenticationHelper,
        private val authenticationManager: AuthenticationManager,
        private val userRepository: UserRepository
) {

    fun login(loginRequestDto: LoginRequestDto): LoginResponseDto {
        try {
            val username = Optional.ofNullable(loginRequestDto.getUsername())
                    .orElseThrow({ BadCredentialsException("Username should be passed.") })
            val password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow({ BadCredentialsException("Password should be passed.") })
            val authRequest = UsernamePasswordAuthenticationToken(username,
                    password)
            val authResult = this.authenticationManager!!.authenticate(authRequest)
            if (authResult.isAuthenticated) {
                val userDetails = authResult.principal as JwtUserDetails
                val user = userRepository!!.findById(userDetails.getId()).orElse(null)
                        ?: throw UserNotFoundException("User " + userDetails.getId() + " not found")
                val token = this.authenticationHelper!!.generateToken(userDetails.getId())
                return LoginResponseDto(token, user)
            } else {
                throw AuthenticationFailedException("Authentication failed.")
            }
        } catch (exception: BadCredentialsException) {
            return LoginResponseDto(null, null)
        }
    }
}