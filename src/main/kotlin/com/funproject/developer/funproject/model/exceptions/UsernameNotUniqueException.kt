package com.funproject.developer.funproject.model.exceptions

class UsernameNotUniqueException(msg: String) : RuntimeException(msg){
    fun getMsg(): String{
        return super.message!!
    }
}