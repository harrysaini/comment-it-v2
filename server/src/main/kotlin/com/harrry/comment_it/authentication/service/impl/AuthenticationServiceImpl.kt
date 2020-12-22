package com.harrry.comment_it.authentication.service.impl

import com.harrry.comment_it.api.model.LoginRequest
import com.harrry.comment_it.api.model.LoginResponse
import com.harrry.comment_it.api.model.User
import com.harrry.comment_it.api.model.UserSignupRequest
import com.harrry.comment_it.authentication.mapper.AuthenticationMapper
import com.harrry.comment_it.authentication.service.api.AuthenticationService
import com.harrry.comment_it.common.db.repository.UserJPARepository
import com.harrry.comment_it.common.exceptions.InvalidRequestDataException
import com.harrry.comment_it.common.jwt.JwtUtil
import com.harrry.comment_it.common.utils.GenericResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
        val authenticationMapper: AuthenticationMapper,
        val userJPARepository: UserJPARepository,
        val authenticationManager: AuthenticationManager,
        val jwtUtil: JwtUtil,
): AuthenticationService {
    override fun signup(userSignupRequest: UserSignupRequest): User {
        val user = authenticationMapper.map(userSignupRequest)

        return authenticationMapper.map(userJPARepository.save(user))
    }

    override fun login(loginRequest: LoginRequest): LoginResponse {
        authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password, emptyList())
        )

        val user = userJPARepository.findByUsername(loginRequest.username)!!

        val token = jwtUtil.generateToken(user)

        return authenticationMapper.map(user, token);
    }

    override fun getUserByUsername(username: String): User {
        val user = userJPARepository.findByUsername(username)
        return if(user != null) {
            authenticationMapper.map(user)
        } else {
            throw InvalidRequestDataException("Invalid username")
        }
    }


}