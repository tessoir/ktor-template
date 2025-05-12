package com.example.shared.ext

import com.example.shared.authorization.AuthorizationViolation
import com.example.shared.authorization.UserPrincipal
import com.example.shared.exception.UnauthorizedException
import com.example.shared.exception.unauthorized
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.auth.principal
import io.ktor.util.pipeline.PipelineContext

private typealias PipelineContextWithCall = PipelineContext<Unit, ApplicationCall>

/**
 * Get [UserPrincipal] from the [ApplicationCall] or throw [UnauthorizedException]
 *
 * @return [UserPrincipal] if present
 * @throws [UnauthorizedException] if principal is not present
 */
fun PipelineContextWithCall.getUserPrincipalOrThrow(): UserPrincipal =
    getUserPrincipal()
        ?: unauthorized(AuthorizationViolation.Unauthorized())

/**
 * Get [UserPrincipal] from the [ApplicationCall]
 *
 * @return [UserPrincipal] or `null` if principal is not present
 */
fun PipelineContextWithCall.getUserPrincipal(): UserPrincipal? = call.principal<UserPrincipal>()
