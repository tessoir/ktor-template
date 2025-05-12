package com.example.shared.ext

import com.auth0.jwt.JWTCreator
import com.example.shared.authorization.JwtConfiguration
import com.example.shared.authorization.TokenType
import com.example.shared.authorization.UserPrincipal
import kotlinx.datetime.Clock
import kotlinx.datetime.toJavaInstant
import kotlin.time.Duration.Companion.milliseconds

fun JWTCreator.Builder.withUserPrincipal(userPrincipal: UserPrincipal): JWTCreator.Builder =
    // TODO: Add more claims
    this
        .withClaim(UserPrincipal.FieldNames.ID, userPrincipal.id)

fun JWTCreator.Builder.withUserId(id: Int): JWTCreator.Builder =
    this
        .withClaim(UserPrincipal.FieldNames.ID, id)

fun JWTCreator.Builder.signWithJwtConfiguration(
    jwtConfiguration: JwtConfiguration,
    tokenType: TokenType = TokenType.ACCESS,
): String {
    val expirationMs =
        when (tokenType) {
            TokenType.REFRESH -> jwtConfiguration.refreshTokenExpirationMs
            TokenType.ACCESS -> jwtConfiguration.accessTokenExpirationMs
        }.milliseconds
    val expirationDate = Clock.System.now() + expirationMs

    return this
        .withSubject(jwtConfiguration.subject)
        .withIssuer(jwtConfiguration.issuer)
        .withExpiresAt(expirationDate.toJavaInstant())
        .sign(jwtConfiguration.algorithm)
}

private fun JWTCreator.Builder.withClaim(
    key: UserPrincipal.FieldNames,
    value: Any,
): JWTCreator.Builder =
    this
        .withClaim(key.name, value.toString())
