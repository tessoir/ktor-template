package com.example.configuration

import com.example.shared.dto.ErrorResponse
import com.example.shared.exception.BadRequestException
import com.example.shared.exception.ForbiddenException
import com.example.shared.exception.NotFoundException
import com.example.shared.exception.UnauthorizedException
import com.example.shared.validation.violation.violationFilter
import io.github.kverify.core.exception.ValidationException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import org.apache.http.auth.AuthenticationException
import org.slf4j.LoggerFactory
import io.ktor.server.plugins.BadRequestException as KtorBadRequestException

private val statusPagesLogger = LoggerFactory.getLogger("StatusPages")

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            cause.printStackTrace()

            call.respondError(
                ErrorResponse<Unit>(
                    status = HttpStatusCode.InternalServerError.value,
                    message = cause.message,
                ),
            )
        }

        exception<BadRequestException> { call, cause ->
            call.respondError(
                ErrorResponse(
                    HttpStatusCode.BadRequest.value,
                    info =
                        listOf(
                            cause.violation.toJsonElement(),
                        ),
                ),
            )
        }

        exception<KtorBadRequestException> { call, cause ->
            call.respondError(
                ErrorResponse<Unit>(
                    HttpStatusCode.BadRequest.value,
                    message = cause.message,
                ),
            )
        }

        exception<NotFoundException> { call, cause ->
            call.respondError(
                ErrorResponse(
                    status = HttpStatusCode.NotFound.value,
                    info =
                        listOf(
                            cause.violation.toJsonElement(),
                        ),
                ),
            )
        }

        exception<AuthenticationException> { call, cause ->
            call.respondError(
                ErrorResponse<Unit>(
                    HttpStatusCode.Unauthorized.value,
                    message = cause.message,
                ),
            )
        }

        exception<UnauthorizedException> { call, cause ->
            call.respondError(
                ErrorResponse(
                    status = HttpStatusCode.NotFound.value,
                    info =
                        listOf(
                            cause.violation.toJsonElement(),
                        ),
                ),
            )
        }

        exception<ForbiddenException> { call, cause ->
            call.respondError(
                ErrorResponse(
                    status = HttpStatusCode.NotFound.value,
                    info =
                        listOf(
                            cause.violation.toJsonElement(),
                        ),
                ),
            )
        }

        exception<ValidationException> { call, cause ->
            val violations = violationFilter(cause.violations, statusPagesLogger)

            call.respondError(
                ErrorResponse(
                    status = HttpStatusCode.BadRequest.value,
                    info = violations.map { it.toJsonElement() },
                ),
            )
        }
    }
}

private suspend inline fun <reified T> ApplicationCall.respondError(errorResponse: ErrorResponse<T>): Unit =
    respond(
        errorResponse.httpStatus,
        errorResponse,
    )
