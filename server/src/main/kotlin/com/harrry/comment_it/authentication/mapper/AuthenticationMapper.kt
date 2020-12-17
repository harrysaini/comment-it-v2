package com.harrry.comment_it.authentication.mapper

import com.harrry.comment_it.api.model.LoginResponse
import com.harrry.comment_it.api.model.UserSignupRequest
import com.harrry.comment_it.common.db.models.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthenticationMapper(
        val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    fun map(userSignupRequest: UserSignupRequest): User {
        return User(
                username = userSignupRequest.username!!,
                password = bCryptPasswordEncoder.encode(userSignupRequest.password)
        )
    }


    fun map(user: User): com.harrry.comment_it.api.model.User {
        return com.harrry.comment_it.api.model.User(
                id = user.id,
                username = user.username
        )
    }

    fun map(user: User, token: String): LoginResponse {
        return LoginResponse(
                user = map(user),
                token= token
        )
    }
}