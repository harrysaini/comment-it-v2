package com.harrry.comment_it.common.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder


enum class StatusCode(val value: Int) {
    SUCCESS(0),
    ERROR(1)

}

data class Status(
        val code: Int,
        val message: String
)

@JsonPropertyOrder(value = ["status", "data", "error"])
class GenericResponse<T>(statusCode: StatusCode, statusMessage: String, data: T? = null, error: T? = null) {
    val status = Status(statusCode.value, statusMessage)

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data  = data

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val error = error


    companion object {
        fun <T> success(data: T): GenericResponse<T> {
            return GenericResponse(StatusCode.SUCCESS,"Success", data);
        }
    }
}