package com.harrry.comment_it.common.exceptions

import com.auth0.jwt.exceptions.SignatureVerificationException
import com.harrry.comment_it.common.utils.GenericResponse
import com.harrry.comment_it.common.utils.StatusCode
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.*


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleHibernateValidationError(ex: MethodArgumentNotValidException): ResponseEntity<GenericResponse<MutableMap<String, String?>>> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        }

        val response = GenericResponse(
                statusCode = StatusCode.ERROR,
                statusMessage = "Validation errors",
                error = errors
        )
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    @ExceptionHandler(BadCredentialsException::class, SignatureVerificationException::class, InternalAuthenticationServiceException::class)
    fun handleBadCredentialsException(ex: Exception): ResponseEntity<GenericResponse<Unit>> {
        val response = GenericResponse<Unit>(StatusCode.ERROR,ex.message.toString())
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response)
    }

    @ExceptionHandler(InvalidRequestDataException::class)
    fun handleInvalidRequestDataException(ex: InvalidRequestDataException): ResponseEntity<GenericResponse<Unit>> {
        val response = GenericResponse<Unit>(StatusCode.ERROR,ex.message.toString())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

}