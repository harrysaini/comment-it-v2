package com.harrry.comment_it.authentication.mapper

import com.harrry.comment_it.api.model.UserSignupRequest
import com.harrry.comment_it.common.db.models.User
import org.springframework.stereotype.Component

@Component
class AuthenticationMapper {

    fun map(userSignupRequest: UserSignupRequest): User {
        return User(
                username = userSignupRequest.username,
                password = userSignupRequest.password
        )
    }


    fun map(user: User): com.harrry.comment_it.api.model.User {
        return com.harrry.comment_it.api.model.User(
                id = user.id,
                username = user.username
        )
    }
}