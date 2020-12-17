package com.harrry.comment_it.api.model

import javax.validation.constraints.NotBlank

data class LoginRequest (
        @NotBlank(message = USERNAME_EMPTY)
        var username: kotlin.String,

        @NotBlank(message = PASSWORD_EMPTY)
        var password: kotlin.String,
) {
    companion object ErrorMessages {
        const val PASSWORD_EMPTY = "password is mandatory"
        const val USERNAME_EMPTY = "username is mandatory"
    }
}