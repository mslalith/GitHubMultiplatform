import com.codingfeline.buildkonfig.compiler.FieldSpec

plugins {
    id(libs.plugins.jetbrainsMultiplatform.get().pluginId)
    id(libs.plugins.jetbrainsNativeCocoapods.get().pluginId)
    id(libs.plugins.androidLibrary.get().pluginId)
    id(libs.plugins.jetbrainsCompose.get().pluginId)
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
//    iosX64()
//    iosArm64()
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

                implementation(libs.bundles.apollo)
                implementation(libs.bundles.voyager)
            }
        }

        @Suppress("UNUSED_VARIABLE")
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

multiplatformResources {
    multiplatformResourcesPackage = "dev.mslalith.githubmultiplatform"
    multiplatformResourcesClassName = "SharedRes"
}

fun getGitHubToken(): String {
    val token = properties["github.token"] as? String
    if (token.isNullOrEmpty()) error("github.token must be provided in gradle.properties file")
    return token
}
