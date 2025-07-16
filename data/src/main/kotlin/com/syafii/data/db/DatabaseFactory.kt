package com.syafii.data.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.v1.jdbc.Database

object DatabaseFactory {
    fun init(url: String, driver: String, user: String, pwd: String) {
        val config = HikariConfig().apply {
            jdbcUrl = url
            username = user
            password = pwd
            this.driverClassName = driver
            maximumPoolSize = 10
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        }
        Database.connect(HikariDataSource(config))
    }
}