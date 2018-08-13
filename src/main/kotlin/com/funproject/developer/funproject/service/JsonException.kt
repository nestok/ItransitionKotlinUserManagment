package com.funproject.developer.funproject.service

class JsonException : RuntimeException {

    constructor(message: String) : super(message) {}

    constructor(message: String, exception: Throwable) : super(message, exception) {}
}
