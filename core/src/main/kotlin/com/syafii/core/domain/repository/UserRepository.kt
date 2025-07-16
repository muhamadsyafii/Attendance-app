package com.syafii.core.domain.repository

import com.syafii.core.domain.model.User

interface UserRepository {
    suspend fun findByUsername(username: String): User?
    suspend fun create(username: String, passwordHash: String, role: String = "user"): User
}