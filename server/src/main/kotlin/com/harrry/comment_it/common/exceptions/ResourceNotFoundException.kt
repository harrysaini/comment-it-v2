package com.harrry.comment_it.common.exceptions

import java.lang.RuntimeException

class ResourceNotFoundException(message: String): RuntimeException(message) {
}