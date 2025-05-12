package com.example.shared.ext

import com.example.shared.exception.BadRequestException
import com.example.shared.validation.violation.UnknownSerializableViolation
import io.ktor.http.Parameters
import java.util.UUID

fun Parameters.getUuidOrThrow(
    parameterName: String,
    throwable: Throwable = BadRequestException(UnknownSerializableViolation("'$parameterName' must be UUID")),
): UUID? =
    getUuid(parameterName)?.getOrElse {
        throw throwable
    }

fun Parameters.getUuid(parameterName: String): Result<UUID>? =
    get(parameterName)?.let {
        runCatching { UUID.fromString(it) }
    }
