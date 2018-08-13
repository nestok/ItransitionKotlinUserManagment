package com.funproject.developer.funproject.security.model

class TokenPayload {
    fun getExp(): Long {
        return this.exp
    }

    fun getUserId(): Long {
        return this.userId
    }

    private val userId: Long
    private val exp: Long

    constructor(userId: Long, exp: Long) {
        this.userId = userId
        this.exp = exp
    }
}