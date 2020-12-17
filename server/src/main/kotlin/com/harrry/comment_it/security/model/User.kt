package com.harrry.comment_it.security.model

import org.springframework.security.core.userdetails.User

class User(
        val id: Int,
        username: String,
        password: String
): User(username, password, emptyList())