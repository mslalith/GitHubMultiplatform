plugins {
    id("com.android.application") version "8.2.0-alpha09" apply false
    id("com.android.library") version "8.2.0-alpha09" apply false
    kotlin("android") version "1.8.21" apply false
    kotlin("multiplatform") version "1.8.21" apply false
    id("com.apollographql.apollo3") version "4.0.0-alpha.1" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
