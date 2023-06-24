plugins {
    id("com.android.application") version libs.versions.agp.get() apply false
    id("com.android.library") version libs.versions.agp.get() apply false
    kotlin("multiplatform") version libs.versions.kotlin.get() apply false
    id("org.jetbrains.compose") version libs.versions.composeMultiplatform.get() apply false
    id("com.apollographql.apollo3") version libs.versions.apollo.get() apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
