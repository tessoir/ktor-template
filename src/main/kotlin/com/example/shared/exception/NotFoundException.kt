package com.example.shared.exception

import com.example.shared.validation.violation.SerializableViolation
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

open class NotFoundException(
    violation: SerializableViolation,
) : TypedException(violation)

/**
 * @throws NotFoundException
 */
fun notFound(violation: SerializableViolation): Nothing = throw NotFoundException(violation)

/**
 * Throws [NotFoundException] if [value] is `false`
 *
 * @param value The condition.
 * @param violationGenerator Lazily evaluated [SerializableViolation].
 * @throws NotFoundException
 */
@OptIn(ExperimentalContracts::class)
inline fun requireOrNotFound(
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
