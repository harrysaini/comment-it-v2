package com.harrry.comment_it.api.model

import com.harrry.comment_it.common.db.models.Comment

data class Comment(
        val id: Int,
        val text: String,
        val replies: MutableList<Comment>? = null,
        val userId: Int

)