plugins {
    id(libs.plugins.jetbrainsMultiplatform.get().pluginId)
    id(libs.plugins.androidApplication.get().pluginId)
    id(libs.plugins.jetbrainsCompose.get().pluginId)
}

kotlin {
    android()
    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val androidMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(libs.androidx.activity.compose)
                implementation(libs.koin.android)
            }
        }
    }
}

android {
    namespace = "dev.mslalith.githubmultiplatform.android"
    compileSdk = 33

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")

    defaultConfig {
        applicationId = "dev.mslalith.githubmultiplatform.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlin {
        jvmToolchain(8)
    }
}
