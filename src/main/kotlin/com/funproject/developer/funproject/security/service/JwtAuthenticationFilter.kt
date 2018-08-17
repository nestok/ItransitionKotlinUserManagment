package com.funproject.developer.funproject.security.service

import com.funproject.developer.funproject.security.handler.RestAuthenticationFailureHandler
import com.funproject.developer.funproject.security.model.JwtAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(authenticationManager: AuthenticationManager) : AbstractAuthenticationProcessingFilter({ request -> true }) {

    init {
        setAuthenticationManager(authenticationManager)
        setAuthenticationFailureHandler(RestAuthenticationFailureHandler())
    }

    @Throws(IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication? {
        try {
            val token = Optional.ofNullable(request.getHeader(AuthenticationHelper.AUTHENTICATION_HEADER))
                    .map { header -> header.substring(7) }.orElse(null)

            if (Objects.isNull(token)) {
                throw BadCredentialsException("Token not found in request's header.")
            }
            val authRequest = JwtAuthenticationToken(token)
            return this.authenticationManager.authenticate(authRequest)
        } catch (exception: AuthenticationException) {
            unsuccessfulAuthentication(request, response, exception)
        }

        return null
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse,
                                          chain: FilterChain?, authResult: Authentication) {
        SecurityContextHolder.getContext().authentication = authResult

        if (this.eventPublisher != null) {
            this.eventPublisher.publishEvent(InteractiveAuthenticationSuccessEvent(authResult, this.javaClass))
        }

        chain!!.doFilter(request, response)
    }
}