package com.syafii.data.table

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime


object UsersTable : Table("users") {
    val id = integer("id").autoIncrement()
    val username = varchar("username", length = 50).uniqueIndex()
    val passwordHash = varchar("password_hash", length = 255)
    val role = varchar("role", length = 20).default("user")
    val isActive = bool("is_active").default(true)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    val lastLogin = datetime("last_login").nullable()
}
