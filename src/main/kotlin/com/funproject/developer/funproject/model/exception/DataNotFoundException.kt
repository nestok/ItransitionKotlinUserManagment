package com.funproject.developer.funproject.model.exception

import org.springframework.context.MessageSourceResolvable

abstract class DataNotFoundException : RuntimeException, MessageSourceResolvable {

    constructor(message: String) : super(message) { }
    constructor(message: String, cause: Throwable) : super(message, cause) { }

    override fun getArguments(): Array<out Any>? = arrayOf()
    override fun getDefaultMessage(): String?  = message

}

class UserNotFoundException(val userId: Long) : DataNotFoundException("User $userId is not found.") {

    override fun getCodes(): Array<out String> = arrayOf("error.userNotFound")
    override fun getArguments(): Array<out Any> = arrayOf(userId)

}

//class TopicNotFoundException(val topicId: Long) : DataNotFoundException("$topicId 번 주제를 찾을 수 없습니다.") {
//
//    override fun getCodes(): Array<out String> = arrayOf("error.topicNotFound")
//    override fun getArguments(): Array<out Any> = arrayOf(topicId)
//
//}