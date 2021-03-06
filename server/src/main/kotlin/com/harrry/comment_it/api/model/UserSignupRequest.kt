package com.harrry.comment_it.api.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class UserSignupRequest (
        @get:NotBlank(message = USERNAME_EMPTY)
            var username: kotlin.String?,

        @get:NotBlank(message = PASSWORD_EMPTY)
            var password: kotlin.String?,
) {
    companion object ErrorMessages {
        const val PASSWORD_EMPTY = "password is mandatory"
        const val USERNAME_EMPTY = "username is mandatory"
    }
}