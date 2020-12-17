package com.harrry.comment_it.common.utils

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import javax.servlet.http.HttpServletResponse

object ResponseHelper {

    private val objectMapper = ObjectMapper()

    fun sendErrorResponse(response: HttpServletResponse,code: StatusCode, message: String, statusCode: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR) {
        val data = GenericResponse<Unit>(code,message)
        sendJsonInResponse(response, data, statusCode)
    }

    private fun sendJsonInResponse(response: HttpServletResponse, data: Any, statusCode: HttpStatus){
        val str = objectMapper.writeValueAsString(data)

        response.status = statusCode.value()
        response.setHeader("Content-Type", "application/json")
        response.writer.write(str)

    }
}