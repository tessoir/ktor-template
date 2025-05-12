package com.example.shared.exception

import com.example.shared.validation.violation.SerializableViolation

open class ForbiddenException(
    violation: SerializableViolation,
) : TypedException(violation)

/**
 * @throws ForbiddenException
 */
fun forbidden(violation: SerializableViolation): Nothing = throw ForbiddenException(violation)
