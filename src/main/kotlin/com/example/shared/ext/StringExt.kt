package com.example.shared.ext

import com.example.shared.exception.BadRequestException
import com.example.shared.validation.violation.UnknownSerializableViolation
import java.util.UUID

fun String.toUuidOrThrow(throwable: Throwable = BadRequestException(UnknownSerializableViolation("'$this' must be UUID"))): UUID =
    toUuidOrNull() ?: throw throwable

fun String.toUuidOrNull(): UUID? =
    try {
        UUID.fromString(this)
    } catch (_: IllegalArgumentException) {
        null
    }
