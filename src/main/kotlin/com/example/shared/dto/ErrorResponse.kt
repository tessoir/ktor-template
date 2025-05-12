package com.example.shared.dto

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse<T>(
    val status: Int,
    val info: T? = null,
    val message: String? = null,
) {
    val httpStatus get() = HttpStatusCode.Companion.fromValue(status)
}
