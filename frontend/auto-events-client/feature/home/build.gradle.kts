import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.serialization)
}

kotlin {
    androidTarget()
    iosArm64()
    iosSimulatorArm64()
    jvm()
    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs()

    sourceSets {
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-okhttp:3.4.1")
        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:3.4.1")
        }
//        webMain.dependencies {
//            implementation("io.ktor:ktor-client-js:2.3.12")
//        }

        commonMain.dependencies {
            implementation("io.ktor:ktor-client-core:3.4.1")
            implementation("io.ktor:ktor-client-content-negotiation:3.4.1")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.4.1")
            implementation("io.ktor:ktor-client-logging:3.4.1")

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.koin.compose)
            implementation(libs.koin.core)

            implementation(projects.core.ui)
            implementation(projects.core.common)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenmodel)

            implementation(libs.kotlinx.serialization.json)

            implementation(libs.kotlinx.dateTime)
        }
    }
}

android {
    namespace = "ru.autoevents.auto_events_client.feature.home"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

compose{
    compose.resources {
        publicResClass = true
    }
}
}