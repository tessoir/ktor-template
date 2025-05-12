package com.example.shared.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapper<T>(
    val data: T,
)
