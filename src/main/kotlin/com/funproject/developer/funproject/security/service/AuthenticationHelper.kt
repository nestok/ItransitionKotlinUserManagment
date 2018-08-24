package com.funproject.developer.funproject.security.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.funproject.developer.funproject.security.exception.InvalidTokenAuthenticationException
import com.funproject.developer.funproject.security.model.TokenPayload
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.jwt.JwtHelper
import org.springframework.security.jwt.crypto.sign.MacSigner
import org.springframework.stereotype.Component
import java.io.IOException
import java.time.Instant
import java.util.*

@Component
@RequiredArgsConstructor
class AuthenticationHelper @Autowired constructor(
        private val objectMapper: ObjectMapper
) {
    private val SECRET = "ChangeMeToSomethingElse"

    private val tokenExpirationTime = 36000000L

    fun generateToken(userId: Long): String {
        try {
            val payload = TokenPayload(
                    userId,
                    Instant.now().epochSecond + this.tokenExpirationTime
            )

            val token = this.objectMapper!!.writeValueAsString(payload)
            return JwtHelper.encode(token, MacSigner(SECRET)).getEncoded()
        } catch (exception: JsonProcessingException) {
            throw InternalAuthenticationServiceException("Error generating token.", exception)
        }

    }

    fun decodeToken(token: String): TokenPayload {
        if (Objects.isNull(token)) {
            throw InvalidTokenAuthenticationException("Token was null or blank.")
        }

        val jwt = JwtHelper.decode(token)

        try {
            jwt.verifySignature(MacSigner(SECRET))
        } catch (exception: Exception) {
            throw InvalidTokenAuthenticationException("Token signature verification failed.", exception)
        }

        val claims = jwt.getClaims()
        val tokenPayload: TokenPayload
        try {
            tokenPayload = this.objectMapper!!.readValue(claims, TokenPayload::class.java)
        } catch (exception: IOException) {
            throw InvalidTokenAuthenticationException("Token parsing failed.", exception)
        }

        return tokenPayload
    }

    companion object {

        val AUTHENTICATION_HEADER = "Authorization"

    }
}