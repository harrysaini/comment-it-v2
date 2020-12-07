package com.harrry.comment_it.common.db.repository

import com.harrry.comment_it.common.db.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJPARepository : JpaRepository<User, String>{
}