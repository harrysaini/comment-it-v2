package com.harrry.comment_it.comments.service.impl

import com.harrry.comment_it.api.model.Comment
import com.harrry.comment_it.api.model.CreateCommentRequest
import com.harrry.comment_it.comments.mapper.CommentsMapper
import com.harrry.comment_it.comments.service.api.CommentsService
import com.harrry.comment_it.common.db.models.User
import com.harrry.comment_it.common.db.repository.CommentsJPARepository
import com.harrry.comment_it.common.exceptions.InvalidRequestDataException
import com.harrry.comment_it.config.CommentsConfig
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
        val commentsJPARepository: CommentsJPARepository,
        val commentsMapper: CommentsMapper
): CommentsService {

    override fun getComment(commentId: Int): Comment {
        val commentOpt = commentsJPARepository.findById(commentId)
        return if (commentOpt.isPresent) {
            val comment = commentOpt.get()
            val replies = findAllReplies(comment)
            commentsMapper.map(comment, replies)
        } else {
            throw InvalidRequestDataException("Comment id invalid")
        }
    }

    override fun getAllComments(): List<Comment> {
        val comments = commentsJPARepository.findAll()
        return runBlocking {
            val withRepliesDeferred = comments.map {
                async {
                    val replies = findAllReplies(it)
                    commentsMapper.map(it, replies)
                }
            }
            withRepliesDeferred.awaitAll()
        }
    }

    override fun createComment(createCommentRequest: CreateCommentRequest, user: User): Comment {
        if (createCommentRequest.parentCommentId != null){
            val comment = addReply(createCommentRequest, user)
            return commentsMapper.map(comment)
        }
        val comment = commentsMapper.map(createCommentRequest, 0, user)
        return commentsMapper.map(commentsJPARepository.save(comment))
    }

    private fun findAllReplies(comment: com.harrry.comment_it.common.db.models.Comment): List<Comment> {
        return runBlocking {
            val comments = comment.replies
            val deferred = comments.map {
                async {
                    val replies = findAllReplies(it)
                    commentsMapper.map(it, replies)
                }

            }
            deferred.awaitAll()
        }

    }

    private fun addReply(createCommentRequest: CreateCommentRequest, user: User): com.harrry.comment_it.common.db.models.Comment {
        val parentCommentOpt = commentsJPARepository.findById(createCommentRequest.parentCommentId!!)
        if(parentCommentOpt.isEmpty) {
            throw InvalidRequestDataException(InvalidRequestDataException.INVALID_PARENT_COMMENT_ID)
        }

        val parentComment = parentCommentOpt.get()
        if (CommentsConfig.LEVEL_RECURSION_LIMIT < parentComment.level) {
            throw InvalidRequestDataException(InvalidRequestDataException.INVALID_LEVEL)
        }

        val level = parentComment.level + 1
        val comment = commentsMapper.map(createCommentRequest, level, user)
        parentComment.addReply(comment)
        commentsJPARepository.save(parentComment)
        return parentComment.replies.last()
    }
}