package com.funproject.developer.funproject.security.service

import com.funproject.developer.funproject.repository.UserRepository
import com.funproject.developer.funproject.security.exception.ExpiredTokenAuthenticationException
import com.funproject.developer.funproject.security.exception.InvalidTokenAuthenticationException
import com.funproject.developer.funproject.security.model.JwtAuthenticationToken
import com.funproject.developer.funproject.security.model.JwtUserDetails
import lombok.RequiredArgsConstructor
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
@RequiredArgsConstructor
class JwtAuthenticationProvider @Autowired constructor(
        private val userRepository: UserRepository,
        private val authenticationHelper: AuthenticationHelper
) : AuthenticationProvider  {

    override fun authenticate(authRequest: Authentication): Authentication {
        val token = StringUtils.trimToNull(authRequest.credentials as String)

        val tokenPayload = authenticationHelper!!.decodeToken(token)

        checkIsExpired(tokenPayload.getExp())

        val userEntityId = tokenPayload.getUserId()
        if (Objects.isNull(userEntityId)) {
            throw InvalidTokenAuthenticationException("Token does not contain a user id.")
        }

        val user = userRepository!!.findById(userEntityId)
        if (Objects.isNull(user)) {
            throw InvalidTokenAuthenticationException("Token does not contain existed user id.")
        }

        val userDetails = JwtUserDetails(user.get())
        return JwtAuthenticationToken(userDetails)
    }

    private fun checkIsExpired(tokenExpirationTime: Long) {
        if (System.currentTimeMillis() / MILLIS_IN_SECOND > tokenExpirationTime) {
            throw ExpiredTokenAuthenticationException()
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return JwtAuthenticationToken::class.java!!.isAssignableFrom(authentication)
    }

    companion object {

        private val MILLIS_IN_SECOND = 1000L
    }
}