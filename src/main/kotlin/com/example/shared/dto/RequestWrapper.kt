package com.example.shared.dto

import kotlinx.serialization.Serializable

@Serializable
data class RequestWrapper<T>(
    val data: T,
)
