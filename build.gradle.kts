plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.ktor)
    alias(libs.plugins.shadowjar)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.bundles.kotlin.libraries)
    implementation(libs.bundles.ktor.server.core)
    implementation(libs.bundles.ktor.server.features)
    implementation(libs.bundles.ktor.client.core)
    implementation(libs.bundles.ktor.client.features)
    implementation(libs.bundles.database)
    implementation(libs.bundles.di)
    implementation(libs.bundles.swagger)
    implementation(libs.bundles.logging)
    implementation(libs.bundles.kverify)
    testImplementation(libs.bundles.testing)
}
