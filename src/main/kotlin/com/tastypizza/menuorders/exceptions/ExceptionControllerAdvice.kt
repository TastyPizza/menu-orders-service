package com.tastypizza.menuorders.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(
        value = [ResourceNotFoundException::class,
            IngredientsOutException::class]
    )
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(ex: RuntimeException): String? {
        return ex.message
    }

}