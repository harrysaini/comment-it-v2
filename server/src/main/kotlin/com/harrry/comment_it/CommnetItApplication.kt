package com.harrry.comment_it

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
class CommentItV2Application

fun main(args: Array<String>) {
	runApplication<CommentItV2Application>(*args)
}
