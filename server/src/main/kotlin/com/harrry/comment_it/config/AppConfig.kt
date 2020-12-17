package com.harrry.comment_it.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "spring.datasource")
data class DatabaseConfig(
        val url: String,
        val username: String,
        val password: String,
)

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
data class JWTConfig(
        val secretKey: String
) {
    companion object {
        const val EXPIRY_TIME = 24 * 60 * 60 * 1000
        const val HEADER_NAME = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
    }

}
