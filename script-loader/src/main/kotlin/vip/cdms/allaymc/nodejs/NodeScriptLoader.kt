package vip.cdms.allaymc.nodejs

import org.allaymc.api.plugin.PluginContainer
import org.allaymc.api.plugin.PluginDescriptor
import org.allaymc.api.plugin.PluginLoader
import java.nio.file.Path

class NodeScriptLoader : PluginLoader {
    override fun getPluginPath(): Path {
        TODO("Not yet implemented")
    }

    override fun loadDescriptor(): PluginDescriptor {
        TODO("Not yet implemented")
    }

    override fun loadPlugin(): PluginContainer {
        TODO("Not yet implemented")
    }

    object Factory : PluginLoader.Factory {
        override fun canLoad(pluginPath: Path?): Boolean {
            TODO("Not yet implemented")
        }

        override fun create(pluginPath: Path?): PluginLoader {
            TODO("Not yet implemented")
        }
    }
}
