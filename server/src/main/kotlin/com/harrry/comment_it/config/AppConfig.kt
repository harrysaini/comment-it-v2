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