plugins {
    kotlin("jvm") version "2.1.0"
    id("com.gradleup.shadow") version "8.3.0"
}

group = "vip.cdms.allayplugin"
description = "Hello Allay from Kotlin!"
version = "0.1.0-alpha"

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
    maven("https://repo.opencollab.dev/maven-releases/")
    maven("https://repo.opencollab.dev/maven-snapshots/")
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
}

dependencies {
    @Suppress("VulnerableLibrariesLocal", "RedundantSuppression")
    compileOnly(group = "org.allaymc.allay", name = "api", version = "master-SNAPSHOT")
}

kotlin {
    jvmToolchain(21)
}
