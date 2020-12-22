package com.harrry.comment_it.api

import com.harrry.comment_it.api.model.LoginRequest
import com.harrry.comment_it.api.model.LoginResponse
import com.harrry.comment_it.api.model.User
import com.harrry.comment_it.api.model.UserSignupRequest
import com.harrry.comment_it.common.utils.GenericResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


interface AuthenticationApi : BaseApi {

    @RequestMapping(
            value = ["/auth/signup"],
            produces = ["application/json"],
            method = [RequestMethod.POST]
    )
    fun signupUserResource(
            @Valid @RequestBody userSignupRequest: UserSignupRequest
    ): ResponseEntity<GenericResponse<User>?> {
        return this.signupUser(userSignupRequest)
    }

    fun signupUser(
            userSignupRequest: UserSignupRequest
    ): ResponseEntity<GenericResponse<User>?>


    @RequestMapping(
            value = ["/auth/login"],
            produces = ["application/json"],
            method = [RequestMethod.POST]
    )
    fun loginUserResource(
            @RequestBody @Valid loginRequest: LoginRequest
    ): ResponseEntity<GenericResponse<LoginResponse>?> {
        return this.loginUser(loginRequest)
    }

    fun loginUser(
            @RequestBody loginRequest: LoginRequest
    ): ResponseEntity<GenericResponse<LoginResponse>?>


    @GetMapping(
            value = ["/auth/me"],
            produces = ["application/json"],
    )
    fun loggedInUserResource(
            authentication: Authentication
    ): ResponseEntity<GenericResponse<User>?> {
        return this.loggedInUser(authentication)
    }

    fun loggedInUser(
            authentication: Authentication
    ): ResponseEntity<GenericResponse<User>?>
}
