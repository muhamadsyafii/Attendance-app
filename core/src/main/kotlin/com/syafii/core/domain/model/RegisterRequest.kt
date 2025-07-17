package com.syafii.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val username: String,
    val password: String,
    val role: String? = "user"
)