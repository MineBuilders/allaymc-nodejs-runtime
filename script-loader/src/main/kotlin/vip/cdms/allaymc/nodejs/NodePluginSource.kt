package vip.cdms.allaymc.nodejs

import org.allaymc.api.plugin.PluginSource
import java.io.File

object NodePluginSource : PluginSource {
    val ScriptDirectory = File("node-plugins").apply { mkdirs() }

    override fun find() = ScriptDirectory
        .listFiles()?.mapTo(mutableSetOf()) { it.toPath() }
        ?: mutableSetOf()
}
