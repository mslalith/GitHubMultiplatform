import io.gitlab.arturbosch.detekt.Detekt

apply(from = "./buildScripts/install-git-hooks.gradle.kts")

plugins {
    id("com.android.application") version libs.versions.agp.get() apply false
    id("com.android.library") version libs.versions.agp.get() apply false
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
    id("org.jetbrains.compose") version libs.versions.composeMultiplatform.get() apply false
    id("com.apollographql.apollo3") version libs.versions.apollo.get() apply false
    id("com.codingfeline.buildkonfig") version libs.versions.buildkonfig.get() apply false
    id("io.gitlab.arturbosch.detekt") version libs.versions.detekt.get()
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
