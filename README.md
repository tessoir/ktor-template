# ktor-template

A modern Ktor project template that includes clean configuration, a robust base structure, and helpful utilities to speed up new project setup.

## Features

- ✅ Modular and scalable project layout
- ✅ Koin for dependency injection
- ✅ Flyway for database migrations
- ✅ Built-in serialization and error handling
- ✅ Swagger (OpenAPI) integration
- ✅ Concise and expressive validation DSL
- ✅ Centralized dependency management using `libs.versions.toml`

## Project Structure
```
.\src\main\kotlin\com\example\
├── app           # Application-specific logic and feature modules
├── configuration # Setup for Koin, Routing, Security, etc.
└── shared        # Utilities, common types, extensions, and shared concerns
```


### Shared Module Highlights

- `authorization/` – JWT handling and user principals
- `dto/` – Standardized request/response wrappers
- `exception/` – Typed exceptions for HTTP errors
- `ext/` – Useful extension functions
- `serialization/` – Custom serializers
- `validation/violation/` – Structured validation system

## Recommended Plugin: DDD Structure Generator

To take full advantage of this template, install [**my plugin**](https://plugins.jetbrains.com/plugin/26064-ktor-ddd-generator) which scaffolds a full DDD-style folder structure:

```
example
├── application
│   ├── dto
│   │   ├── ExampleRequest.kt
│   │   └── ExampleResponse.kt
│   ├── route
│   │   └── ExampleRoutes.kt
│   └── usecase
│       └── ExampleUseCaseImpl.kt
├── domain
│   ├── entity
│   │   └── ExampleEntity.kt
│   ├── repository
│   │   └── ExampleRepository.kt
│   └── usecase
│       └── ExampleUseCase.kt
└── infrastructure
    ├── persistence
    │   └── ExampleTable.kt
    └── repository
        └── ExampleRepositoryImpl.kt
```


This plugin reflects the architecture I’ve built and refined over 1.5 years of working with Ktor. It helps you stay organized and keeps your business logic isolated from infrastructure concerns.

> ⚠️ Highly recommended for production-grade services or clean API design.

# License
MIT – feel free to use and adapt it.