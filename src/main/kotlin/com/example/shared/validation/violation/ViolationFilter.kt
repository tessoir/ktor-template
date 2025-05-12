package com.example.shared.validation.violation

import io.github.kverify.core.violation.Violation
import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val violationFilterLogger by lazy { LoggerFactory.getLogger("Violation Filter") }

fun violationFilter(
    violations: List<Violation>,
    logger: Logger = violationFilterLogger,
): List<SerializableViolation> {
    val (codeViolations, unknownViolations) =
        violations.partition {
            it is SerializableViolation
        }

    @Suppress("UNCHECKED_CAST")
    return if (unknownViolations.isNotEmpty()) {
        logger.atWarn().log("Some violations are not PropertyViolation")
        codeViolations + unknownViolations.map { UnknownSerializableViolation(it.reason) }
    } else {
        codeViolations
    } as List<SerializableViolation>
}
