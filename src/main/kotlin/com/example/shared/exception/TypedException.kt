package com.example.shared.exception

import com.example.shared.validation.violation.SerializableViolation

open class TypedException(
    val violation: SerializableViolation,
    cause: Throwable? = null,
) : Throwable(
        violation.reason,
        cause,
    )
