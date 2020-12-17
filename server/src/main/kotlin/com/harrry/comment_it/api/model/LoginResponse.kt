package com.harrry.comment_it.api.model

data class LoginResponse(
        val user: User,
        val token: String
)