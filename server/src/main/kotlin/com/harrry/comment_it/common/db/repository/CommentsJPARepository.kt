package com.harrry.comment_it.common.db.repository

import com.harrry.comment_it.common.db.models.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentsJPARepository: JpaRepository<Comment, Int> {
    fun findByLevel(level: Int): List<Comment>
}