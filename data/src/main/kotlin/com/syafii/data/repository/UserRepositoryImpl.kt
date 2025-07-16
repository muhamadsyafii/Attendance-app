package com.syafii.data.repository

import com.syafii.core.domain.model.User
import com.syafii.core.domain.repository.UserRepository
import com.syafii.data.table.UsersTable
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class UserRepositoryImpl : UserRepository {
    override suspend fun findByUsername(username: String): User? = transaction {
        UsersTable
            .selectAll()
            .where { UsersTable.username eq username }
            .map { row ->
                User(
                    id           = row[UsersTable.id],
                    username     = row[UsersTable.username],
                    passwordHash = row[UsersTable.passwordHash],
                    role         = row[UsersTable.role]
                )
            }
            .singleOrNull()
    }

    override suspend fun create(username: String, passwordHash: String, role: String): User = transaction {
        val id = UsersTable.insert {
            it[UsersTable.username]     = username
            it[UsersTable.passwordHash] = passwordHash
            it[UsersTable.role]         = role
        } get UsersTable.id
        User(id, username, passwordHash, role)
    }
}
