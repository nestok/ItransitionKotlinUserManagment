package com.funproject.developer.funproject.security.exception

import org.springframework.security.core.AuthenticationException


class ExpiredTokenAuthenticationException : AuthenticationException("Authentication token is expired.")
