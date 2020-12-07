package com.harrry.comment_it.authentication.service.api

import com.harrry.comment_it.api.model.User
import com.harrry.comment_it.api.model.UserSignupRequest

interface AuthenticationService {
    fun signup(userSignupRequest: UserSignupRequest): User
}