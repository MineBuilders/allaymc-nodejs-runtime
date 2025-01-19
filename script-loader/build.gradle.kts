plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.gradleup.shadow)
}

dependencies {
    @Suppress("VulnerableLibrariesLocal", "RedundantSuppression")
    compileOnly(libs.allaymc.server)

    // ktx core shared lib
    val sharedLibs = arrayOf(
        libs.kotlin.stdlib.asProvider().get(),
        libs.kotlin.stdlib.jdk7.get(),
        libs.kotlin.stdlib.jdk8.get(),
        libs.kotlin.reflect.get(),
        libs.kotlinx.coroutines.core.get(),
    )
    sharedLibs.forEach { compileOnly(it) }
    val excludeSharedLibs = Action<ExternalModuleDependency> {
        sharedLibs.forEach { exclude(group = it.group, module = it.name) }
    }

    implementation(libs.caoccao.javet)
    implementation(libs.caoccao.javet.get().run { "$module-node-windows-x86_64:$version" })
}
