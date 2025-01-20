package vip.cdms.allaymc.nodejs

import org.allaymc.api.plugin.PluginSource
import java.nio.file.Path
import kotlin.io.path.name

object NodePluginSource : PluginSource {
    val ScriptDirectory = Path.of("node-plugins").mkdirs()
    val DataDirectory = Path.of("node-plugin-data").mkdirs()

    private fun Path.mkdirs() = apply { toFile().mkdirs() }

    fun getDataDirectory(plugin: NodePlugin): Path = DataDirectory
        .resolve(plugin.jsDoc.properties["module"] ?: plugin.packageJson.name ?: plugin.path.name)
        .mkdirs()

    override fun find() = ScriptDirectory.toFile()
        .listFiles()?.map { it.toPath() }?.toSet() ?: emptySet()
}
