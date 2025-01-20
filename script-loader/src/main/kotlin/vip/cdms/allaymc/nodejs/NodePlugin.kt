package vip.cdms.allaymc.nodejs

import org.allaymc.api.plugin.Plugin
import vip.cdms.allaymc.nodejs.utils.JsDoc
import vip.cdms.allaymc.nodejs.utils.PackageJson
import java.nio.file.Path

class NodePlugin(
    val path: Path,
    val jsDoc: JsDoc,
    val packageJson: PackageJson,
) : Plugin() {
}
