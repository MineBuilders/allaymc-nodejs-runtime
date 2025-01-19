package vip.cdms.allaymc.nodejs

import org.allaymc.server.extension.Extension
import org.allaymc.server.plugin.AllayPluginManager

@Suppress("unused")
class NodePluginExtension : Extension() {
    override fun main(args: Array<out String>?) {
        AllayPluginManager.registerSource(NodePluginSource)
        AllayPluginManager.registerLoaderFactory(NodePluginLoader.Factory)
    }
}
