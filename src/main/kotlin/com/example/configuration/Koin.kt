package com.example.configuration

import com.example.shared.koin.ApplicationConfiguration
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {
    install(Koin) {
        properties(
            ApplicationConfiguration { configurationProperties },
        )

        modules(
            koin.getRepositoryModule(),
            koin.getUseCaseModule(),
        )
    }
}

fun Koin.getRepositoryModule(): Module =
    module {
        // TODO: Add repositories
    }

fun Koin.getUseCaseModule(): Module =
    module {
        // TODO: Add use cases
    }
