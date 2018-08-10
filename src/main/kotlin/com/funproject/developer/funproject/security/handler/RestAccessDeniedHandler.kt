package com.funproject.developer.funproject.security.handler

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RestAccessDeniedHandler : AccessDeniedHandler {

    @Throws(IOException::class)
    override fun handle(request: HttpServletRequest, response: HttpServletResponse,
                        exception: AccessDeniedException) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, DENIED_MESSAGE)
    }

    companion object {
        private val DENIED_MESSAGE = "Sorry, you don't have required permission for this operation."
    }
}
