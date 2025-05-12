package com.example.shared.exception

import com.example.shared.validation.violation.SerializableViolation
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

open class BadRequestException(
    violation: SerializableViolation,
) : TypedException(violation)

/**
 * @throws BadRequestException
 */
fun badRequest(violation: SerializableViolation): Nothing = throw BadRequestException(violation)

/**
 * Throws [BadRequestException] if [value] is `false`
 *
 * @param value The condition.
 * @param violationGenerator Lazily evaluated [SerializableViolation]
 * @throws BadRequestException
 */
@OptIn(ExperimentalContracts::class)
inline fun requireOrBadRequest(
    value: Boolean,
    violationGenerator: () -> SerializableViolation,
) {
    contract {
        returns() implies (value)
    }

    if (!value) {
        val violation = violationGenerator()
        throw NotFoundException(violation)
    }
}
