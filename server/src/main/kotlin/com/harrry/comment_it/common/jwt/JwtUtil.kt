package com.harrry.comment_it.common.jwt

import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.harrry.comment_it.config.JWTConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Component
@EnableConfigurationProperties(value = [JWTConfig::class])
class JwtUtil(
        val jwtConfig: JWTConfig
) {

    fun generateToken(user: com.harrry.comment_it.common.db.models.User): String {
        return JWT.create()
                .withSubject(user.username)
                .withExpiresAt(Date(System.currentTimeMillis() + JWTConfig.EXPIRY_TIME))
                .sign(Algorithm.HMAC512(jwtConfig.secretKey))

    }
}