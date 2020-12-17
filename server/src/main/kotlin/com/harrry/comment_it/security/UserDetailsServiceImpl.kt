package com.harrry.comment_it.security

import com.harrry.comment_it.common.db.repository.UserJPARepository
import com.harrry.comment_it.common.exceptions.ResourceNotFoundException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
        private val userJPARepository: UserJPARepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
            val user = userJPARepository.findByUsername(username)
            return if(user != null){
                User(user.username, user.password, emptyList())
            } else {
                throw ResourceNotFoundException("Username not found")
            }

    }
}