package com.example.configuration

import com.auth0.jwt.interfaces.Payload
import com.example.shared.authorization.UserPrincipal
import com.example.shared.ext.getClaim
import com.example.shared.ext.toUuidOrNull
import com.example.shared.koin.injectJwtConfiguration
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.jwt
import org.koin.ktor.ext.getKoin

fun Application.configureSecurity() {
    val verifier = getKoin().injectJwtConfiguration().verifier

    install(Authentication) {
        jwt {
            verifier(verifier)

            validate {
                runCatching {
                    it.payload.toUserPrincipal()
                }.getOrNull()
            }
        }
    }
}

private fun Payload.toUserPrincipal(): UserPrincipal =
    UserPrincipal(
        id =
            getClaim(UserPrincipal.FieldNames.ID)?.toUuidOrNull()
                ?: error("Claim ${UserPrincipal.FieldNames.ID} must be UUID"),
    )
