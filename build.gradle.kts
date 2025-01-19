plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.kotlin.jvm) apply false
}

version = libs.versions.allaymc.nodejs.runtime.get()
subprojects { version = rootProject.version }
