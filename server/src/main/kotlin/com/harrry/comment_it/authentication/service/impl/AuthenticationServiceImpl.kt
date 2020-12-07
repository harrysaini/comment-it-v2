package com.harrry.comment_it.authentication.service.impl

import com.harrry.comment_it.api.model.User
import com.harrry.comment_it.api.model.UserSignupRequest
import com.harrry.comment_it.authentication.mapper.AuthenticationMapper
import com.harrry.comment_it.authentication.service.api.AuthenticationService
import com.harrry.comment_it.common.db.repository.UserJPARepository
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
        val authenticationMapper: AuthenticationMapper,
        val userJPARepository: UserJPARepository
): AuthenticationService {
    override fun signup(userSignupRequest: UserSignupRequest): User {
        val user = authenticationMapper.map(userSignupRequest)

        return authenticationMapper.map(userJPARepository.save(user))
    }
}