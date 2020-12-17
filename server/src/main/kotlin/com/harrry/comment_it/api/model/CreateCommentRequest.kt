package com.harrry.comment_it.api.model

import javax.validation.constraints.NotBlank

data class CreateCommentRequest (
        @get:NotBlank(message = TEXT_EMPTY)
        val text: String? = null,

        val parentCommentId: Int? = null
) {
    companion object {
        const val TEXT_EMPTY = "Text can't be empty"
    }
}
