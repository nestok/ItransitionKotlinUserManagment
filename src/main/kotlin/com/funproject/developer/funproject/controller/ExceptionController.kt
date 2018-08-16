package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.model.exception.*
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ResponseStatus

@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleBadCredentialsException(ex: BadCredentialsException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(AuthenticationFailedException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    fun handleAuthenticationFailedException(ex: AuthenticationFailedException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(UsernameNotUniqueException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleUsernameNotUniqueException(ex: UsernameNotUniqueException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(EmailNotUniqueException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleEmailNotUniqueException(ex: EmailNotUniqueException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(AdminDeleteAttemptException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleAdminDeleteAttemptException(ex: AdminDeleteAttemptException): ResponseEntity<String> {
        return ResponseEntity(ex.message, HttpStatus.FORBIDDEN)
    }

}