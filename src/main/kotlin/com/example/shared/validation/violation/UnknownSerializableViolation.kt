package com.example.shared.validation.violation

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement

@Serializable
class UnknownSerializableViolation(
    override val reason: String,
) : SerializableViolation {
    override val code = "UNKNOWN"

    override fun toJsonElement(json: Json): JsonElement = json.encodeToJsonElement(this)
}
