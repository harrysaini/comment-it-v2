package com.harrry.comment_it.common.exceptions

class InvalidRequestDataException(message: String) : RuntimeException(message) {
    companion object MESSAGES {
        const val INVALID_PARENT_COMMENT_ID = "No comment found for parentCommentId"
        const val INVALID_LEVEL = "Reply level limit reached"
    }
}