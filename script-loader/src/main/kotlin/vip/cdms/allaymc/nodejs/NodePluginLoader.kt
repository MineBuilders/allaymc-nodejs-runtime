package vip.cdms.allaymc.nodejs

import org.allaymc.api.plugin.PluginContainer
import org.allaymc.api.plugin.PluginDependency
import org.allaymc.api.plugin.PluginDescriptor
import org.allaymc.api.plugin.PluginLoader
import vip.cdms.allaymc.nodejs.utils.JsDoc
import vip.cdms.allaymc.nodejs.utils.PackageJson
import vip.cdms.allaymc.nodejs.utils.parseScriptHeader
import vip.cdms.allaymc.nodejs.utils.plus
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.io.path.readText

class NodePluginLoader(
    val path: Path,
    val jsDoc: JsDoc,
    val packageJson: PackageJson,
) : PluginLoader {
    override fun getPluginPath() = path

    private val pluginDescriptor by lazy {
        object : PluginDescriptor {
            override fun getName() = jsDoc.properties["name"] ?: packageJson.name ?: path.name
            override fun getEntrance() = packageJson.main ?: path.name
            override fun getDescription() = jsDoc.content ?: packageJson.description ?: ""
            override fun getVersion() = jsDoc.properties["version"] ?: packageJson.version ?: "0.0.1-alpha"
            override fun getAuthors() = jsDoc.properties["author"]?.split(",")?.map(String::trim)
                ?: packageJson.getAuthors() ?: mutableListOf("Anonymous")
            override fun getDependencies() = packageJson.allayDependencies?.map { PluginDependency(it.key, it.value, false) }
            override fun getWebsite() = jsDoc.properties["see"] ?: packageJson.homepage ?: ""
        }
    }
    override fun loadDescriptor() = pluginDescriptor

    override fun loadPlugin(): PluginContainer {
        TODO("Not yet implemented")
    }

    object Factory : PluginLoader.Factory {
        override fun canLoad(path: Path) = path.startsWith(NodePluginSource.ScriptDirectory.toPath()) &&
                (Files.isDirectory(path) && Files.exists(path.resolve("package.json"))
                        || path.toString().matches(Regex(".*\\.(js|cjs|mjs)\$")))
        override fun create(path: Path): NodePluginLoader {
            val (header, packageJson) = if (path.toFile().isFile) {
                val header = parseScriptHeader(path.toFile().readText())
                header to header.packageJson
            } else {
                val packageJson = PackageJson.parseFrom(path.resolve("package.json").readText())
                val main = packageJson.main ?: error("Unknown plugin start point")
                val header = parseScriptHeader(path.resolve(main).readText())
                header to PackageJson(packageJson.raw + header.packageJson.raw)
            }
            return NodePluginLoader(path, header.descriptor, packageJson)
        }
    }
}
