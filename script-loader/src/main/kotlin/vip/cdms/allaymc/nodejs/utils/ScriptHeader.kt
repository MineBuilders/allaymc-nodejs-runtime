package vip.cdms.allaymc.nodejs.utils

data class SingleScriptHeader(
    val descriptor: JsDoc,
    val packageJson: PackageJson,
)

fun parseScriptHeader(code: String): SingleScriptHeader {
    val doc = parseJsDoc(code)
    val content = StringBuilder()
    val packageJson = StringBuilder()

    var isContent = true

    var i = 0
    if (doc.content != null) while (i < doc.content.length) {
        val c = doc.content[i]

        fun subNextTo(length: Int) = runCatching { doc.content.substring(i+1..i+length) }.getOrNull()
        if (c == '\n' && subNextTo(7) == "```json") {
            isContent = false
            i += 8
        } else if (c == '\n' && !isContent && subNextTo(3) == "```") {
            isContent = true
            i += 4
        } else if (isContent) {
            content.append(c)
        } else {
            packageJson.append(c)
        }

        i++
    }

    val json = PackageJson.parseFrom(packageJson.toString())
    return SingleScriptHeader(doc.copy(content = content.toString().takeIf { it.isNotBlank() }), json)
}
