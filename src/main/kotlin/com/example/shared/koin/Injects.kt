package com.example.shared.koin

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.shared.authorization.JwtConfiguration
import org.koin.core.Koin

fun Koin.injectJwtConfiguration(): JwtConfiguration {
    val algorithm =
        getProperty<String>(ApplicationConfiguration.JWT_SECRET)
            .let { Algorithm.HMAC256(it) }

    val verifier =
        JWT
            .require(algorithm)
            .withIssuer(getProperty(ApplicationConfiguration.JWT_ISSUER))
            .build()

    return JwtConfiguration(
        subject = getProperty(ApplicationConfiguration.JWT_SUBJECT),
        issuer = getProperty(ApplicationConfiguration.JWT_ISSUER),
        algorithm = algorithm,
        accessTokenExpirationMs = getProperty(ApplicationConfiguration.JWT_ACCESS_TOKEN_EXPIRATION),
        refreshTokenExpirationMs = getProperty(ApplicationConfiguration.JWT_REFRESH_TOKEN_EXPIRATION),
        verifier = verifier,
    )
}

fun <T> Koin.getProperty(key: ApplicationConfiguration): T =
    getProperty(key.name)
        ?: error("Property ${key.name} not found")
