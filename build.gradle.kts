import io.gitlab.arturbosch.detekt.Detekt

apply(from = "./buildScripts/install-git-hooks.gradle.kts")

plugins {
    id(libs.plugins.androidApplication.get().pluginId) version libs.versions.agp.get() apply false
    id(libs.plugins.androidLibrary.get().pluginId) version libs.versions.agp.get() apply false
    id(libs.plugins.jetbrainsMultiplatform.get().pluginId) version libs.versions.kotlin.get() apply false
    id(libs.plugins.jetbrainsCompose.get().pluginId) version libs.versions.composeMultiplatform.get() apply false
    id(libs.plugins.apollo.get().pluginId) version libs.versions.apollo.get() apply false
    id(libs.plugins.buildkonfig.get().pluginId) version libs.versions.buildkonfig.get() apply false
    id(libs.plugins.detekt.get().pluginId) version libs.versions.detekt.get()
}

allprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.from(files("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

tasks.withType<Detekt> {
    setSource(files(project.projectDir))
    exclude("**/build/**")
    exclude {
        it.file.relativeTo(projectDir).startsWith(project.buildDir.relativeTo(projectDir))
    }
}

tasks.register("detektAll") {
    dependsOn(tasks.withType<Detekt>())
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
