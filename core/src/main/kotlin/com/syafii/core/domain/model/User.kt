package com.syafii.core.domain.model

data class User(
    val id: Int,
    val username: String,
    val passwordHash: String,
    val role: String
)