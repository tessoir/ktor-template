package com.example.shared.exception

import com.example.shared.validation.violation.SerializableViolation

open class UnauthorizedException(
    violation: SerializableViolation,
) : TypedException(violation)

/**
 * @throws UnauthorizedException
 */
fun unauthorized(violation: SerializableViolation): Nothing = throw UnauthorizedException(violation)
