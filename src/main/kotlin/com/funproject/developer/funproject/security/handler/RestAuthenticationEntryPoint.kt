package com.funproject.developer.funproject.security.handler

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RestAuthenticationEntryPoint : AuthenticationEntryPoint {

    @Throws(IOException::class)
    override fun commence(request: HttpServletRequest, response: HttpServletResponse,
                          exception: AuthenticationException) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.message)
    }
}
