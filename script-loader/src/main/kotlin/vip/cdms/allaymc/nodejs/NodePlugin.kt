package vip.cdms.allaymc.nodejs

import com.caoccao.javet.interop.executors.IV8Executor
import org.allaymc.api.plugin.Plugin
import vip.cdms.allaymc.nodejs.utils.JsDoc
import vip.cdms.allaymc.nodejs.utils.PackageJson
import java.nio.file.Path
import kotlin.io.path.name

class NodePlugin(
    val path: Path,
    val mainCode: String,
    val jsDoc: JsDoc,
    val packageJson: PackageJson,
) : Plugin() {
    val name = jsDoc.properties["module"] ?: packageJson.name ?: path.name

    private val isSingleScript = path.toFile().isFile
    private val isCommonJs =
        if (isSingleScript) path.toString().endsWith(".cjs", ignoreCase = true)
        else packageJson.module?.not() ?: true

    lateinit var nodeExecutor: IV8Executor
    override fun onLoad() {
        // TODO: module system
//        val main = if (isSingleScript) path else path.resolve(packageJson.main ?: "index.js")

        nodeExecutor = NodeEnvironment.NodeRuntime.getExecutor(mainCode)
        // FIXME: IO occupied
//        nodeExecutor.setResourceName(main.toString())
        nodeExecutor.isModule = !isCommonJs
        nodeExecutor.executeVoid()
    }

    override fun onEnable() {
    }

    override fun onDisable() {
    }

    // TODO: reloadable
//    override fun isReloadable() = true
//    override fun reload() {
//    }
}
