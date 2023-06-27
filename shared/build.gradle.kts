import com.codingfeline.buildkonfig.compiler.FieldSpec
import java.io.FileInputStream
import java.util.Properties

plugins {
    id(libs.plugins.jetbrainsMultiplatform.get().pluginId)
    id(libs.plugins.jetbrainsNativeCocoapods.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.jetbrainsCompose.get().pluginId)
    id(libs.plugins.kotlinx.serialization.get().pluginId)
    id(libs.plugins.apollo.get().pluginId)
    id(libs.plugins.buildkonfig.get().pluginId)
    id(libs.plugins.moko.resources.get().pluginId)
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
        ios.deploymentTarget = "15.2"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true

            export(libs.moko.resources)
            export(libs.moko.graphics)
        }
    }
    
    sourceSets {
        @Suppress("UNUSED_VARIABLE")
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)

                api(libs.moko.resources.compose)
                implementation(libs.composeIcons.octicons)

                implementation(libs.koin.core)
                implementation(libs.koin.compose)

                implementation(libs.bundles.apollo)
                implementation(libs.bundles.ktor)
                implementation(libs.bundles.voyager)
                implementation(libs.bundles.multiplatformSettings)
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.android)
                implementation(libs.androidx.security.crypto)
            }
        }

        @Suppress("UNUSED_VARIABLE")
        val iosMain by getting {
            dependencies {
                implementation(libs.ktor.darwin)
            }
        }
    }
}

val secretProperties = loadSecretProperties()

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
        buildConfigField(FieldSpec.Type.STRING, "GITHUB_TOKEN", secretProperties["github.token"].toString())
        buildConfigField(FieldSpec.Type.STRING, "GITHUB_CLIENT_ID", secretProperties["github.client_id"].toString())
        buildConfigField(FieldSpec.Type.STRING, "GITHUB_CLIENT_SECRET", secretProperties["github.client_secret"].toString())
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "dev.mslalith.githubmultiplatform"
    multiplatformResourcesClassName = "SharedRes"
}

fun loadSecretProperties(): Properties {
    val secretPropertiesFile = rootProject.file("secret.properties")
    if (secretPropertiesFile.exists().not()) error("secret.properties file is required for GitHub configuration")

    val localProperties = Properties().apply { load(FileInputStream(secretPropertiesFile)) }
    if (localProperties["github.token"] == null) error("github.token field is required in secret.properties")
    if (localProperties["github.client_id"] == null) error("github.client_id field is required in secret.properties")
    if (localProperties["github.client_secret"] == null) error("github.client_secret field is required in secret.properties")

    return localProperties
}
