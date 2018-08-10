package com.funproject.developer.funproject.model.exceptions

class EmailNotUniqueException(msg: String) : RuntimeException(msg){
    fun getMsg(): String{
        return super.message!!
    }
}