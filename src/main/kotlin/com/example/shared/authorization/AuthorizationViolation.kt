package com.example.shared.authorization

import com.example.shared.validation.violation.SerializableViolation
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
sealed class AuthorizationViolation(
    val message: String,
) : SerializableViolation {
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    override val code = this::class.simpleName!!

    override fun toJsonElement(json: Json): JsonElement = json.encodeToJsonElement(this)

    @Serializable
    data class Unauthorized(
        override val reason: String = "You must be authorized",
    ) : AuthorizationViolation(reason)
}
