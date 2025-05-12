package com.example.shared.authorization

import com.example.shared.serialization.SerializedUUID
import io.ktor.server.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
data class UserPrincipal(
    val id: SerializedUUID,
) : Principal {
    enum class FieldNames {
        ID,
    }
}
