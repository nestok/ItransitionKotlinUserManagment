package com.funproject.developer.funproject.security.handler

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RestAuthenticationFailureHandler : AuthenticationFailureHandler {

    @Throws(IOException::class)
    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse,
                                         exception: AuthenticationException) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.message)
    }
}
