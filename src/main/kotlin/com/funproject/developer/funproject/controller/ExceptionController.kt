package com.funproject.developer.funproject.controller

import com.funproject.developer.funproject.model.exception.*
import com.funproject.developer.funproject.model.exception.ErrorResponseEntity.Companion.notFound
import org.springframework.context.MessageSource
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*


@RestControllerAdvice
class ExceptionController(var messageSource: MessageSource) {

    @ExceptionHandler(DataNotFoundException::class)
    fun resourceNotFoundException(exception: DataNotFoundException, locale: Locale) =
            notFound(messageSource.getMessage(exception, locale))

    @ExceptionHandler(UsernameNotUniqueException::class)
    fun handleUsernameNotUniqueException(ex: UsernameNotUniqueException): ResponseMessage {
        return ResponseMessage(ex.message!!)
    }

    @ExceptionHandler(EmailNotUniqueException::class)
    fun handleEmailNotUniqueException(ex: EmailNotUniqueException): ResponseMessage {
        return ResponseMessage(ex.message!!)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseMessage {
        return ResponseMessage(ex.message!!)
    }

    @ExceptionHandler(AdminDeleteAttemptException::class)
    fun handleAdminDeleteAttemptException(ex: AdminDeleteAttemptException): ResponseMessage {
        return ResponseMessage(ex.message!!)
    }

}