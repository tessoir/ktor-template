package com.example.shared.authorization

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

data class JwtConfiguration(
    val subject: String,
    val issuer: String,
    val algorithm: Algorithm,
    val accessTokenExpirationMs: Long,
    val refreshTokenExpirationMs: Long,
    val verifier: JWTVerifier,
)
