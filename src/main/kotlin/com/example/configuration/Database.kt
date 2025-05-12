package com.example.configuration

import com.example.shared.koin.ApplicationConfiguration
import com.example.shared.koin.getProperty
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.ktor.ext.getKoin

private val tableRegistry: Array<Table> =
    arrayOf(
        // TODO: Add tables or use flyway migrations
    )

fun Application.configureDatabase(): Unit =
    with(getKoin()) {
        val url = getProperty<String>(ApplicationConfiguration.DATABASE_URL)
        val user = getProperty<String>(ApplicationConfiguration.DATABASE_USER)
        val password = getProperty<String>(ApplicationConfiguration.DATABASE_PASSWORD)
        val driver = getProperty<String>(ApplicationConfiguration.DATABASE_DRIVER)

        val connection =
            Database.connect(
                url = url,
                user = user,
                password = password,
                driver = driver,
            )

        // TODO: Replace with migrations
        transaction(connection) {
            @Suppress("DEPRECATION")
            SchemaUtils.createMissingTablesAndColumns(tables = tableRegistry)
        }

//  TODO: Migrations are the preferred option for production
//        Flyway
//            .configure()
//            .dataSource(url, user, password)
//            .locations("classpath:db/migration")
//            .load()
//            .migrate()
    }
