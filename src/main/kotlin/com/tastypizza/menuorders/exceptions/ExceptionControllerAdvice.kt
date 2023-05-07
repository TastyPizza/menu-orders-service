package com.tastypizza.menuorders.exceptions

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(
        value = [ResourceNotFoundException::class,
            IngredientsOutException::class]
    )
    fun handleNotFoundException(ex: RuntimeException): String? {
        return ex.message
    }

}