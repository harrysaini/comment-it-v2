package com.harrry.comment_it.api.model

data class Comment(
        val id: Int,
        val text: String,
        val replies: List<Comment> = emptyList(),
        val user: User,
        val level: Int,
        var createdAt: String,
        var updatedAt: String,

)