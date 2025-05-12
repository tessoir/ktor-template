package com.example.shared.ext

import com.auth0.jwt.interfaces.Payload
import com.example.shared.authorization.UserPrincipal

fun Payload.getClaim(key: UserPrincipal.FieldNames): String? = getClaim(key.name).asString()
