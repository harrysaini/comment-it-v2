package com.harrry.comment_it.security

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.harrry.comment_it.common.db.repository.UserJPARepository
import com.harrry.comment_it.common.exceptions.InvalidAuthException
import com.harrry.comment_it.config.JWTConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthorizationFilter(
        private val jwtConfig: JWTConfig,

        @Autowired
        private val userJPARepository: UserJPARepository
): OncePerRequestFilter() {
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, filterChain: FilterChain) {
        val header = req.getHeader(JWTConfig.HEADER_NAME)

        if (header == null || !header.startsWith(JWTConfig.TOKEN_PREFIX)) {
            filterChain.doFilter(req, res)
            return
        }

        val authentication = getAuthentication(req)

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(req, res)
    }

    private fun getAuthentication(req: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val header = req.getHeader(JWTConfig.HEADER_NAME)

        return header?.let {
            val username = JWT.require(Algorithm.HMAC512(jwtConfig.secretKey))
                    .build()
                    .verify(it.removePrefix(JWTConfig.TOKEN_PREFIX))
                    .subject

            var user = userJPARepository.findByUsername(username)
            return if(user != null ){
                UsernamePasswordAuthenticationToken(user, null, emptyList())
            } else {
                throw InvalidAuthException("Invalid token")
            }
        }
    }

}