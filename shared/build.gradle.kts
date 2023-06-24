import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.apollographql.apollo3")
    id("com.codingfeline.buildkonfig")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                api(compose.components.resources)

                implementation(libs.apollo.runtime)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "dev.mslalith.githubmultiplatform"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
    }
}

apollo {
    service("github") {
        packageName.set("dev.mslalith.githubmultiplatform")
    }
}

buildkonfig {
    packageName = "dev.mslalith.githubmultiplatform"
    defaultConfigs {
        buildConfigField(FieldSpec.Type.STRING, "GITHUB_TOKEN", getGitHubToken())
    }
}

fun getGitHubToken(): String {
    val token = properties["github.token"] as? String
    if (token.isNullOrEmpty()) throw IllegalStateException("github.token must be provided in gradle.properties file")
    return token
}
