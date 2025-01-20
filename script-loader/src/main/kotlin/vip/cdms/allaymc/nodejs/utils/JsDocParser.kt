package vip.cdms.allaymc.nodejs.utils

data class JsDoc(
    val content: String?,
    val properties: Map<String, String>,
)

fun parseJsDoc(doc: String): JsDoc {
    val content = StringBuilder()
    val properties = mutableMapOf<String, String>()

    var begin = false
    var isLineBegin = true
    var isTrimStarting = false
    var isContent = true
    var isPropertyKey = false
    val propertyKey = StringBuilder()
    val propertyValue = StringBuilder()
    var droppedFirstEmptyProperty = false

    fun saveProperty() {
        properties[propertyKey.toString()] = propertyValue.toString().trimEnd()
        propertyKey.clear()
        propertyValue.clear()
    }

    var i = 0
    while (i < doc.length) {
        val c = doc[i]

        if (!begin && c == '/' && doc[i + 1] == '*' && doc[i + 2] == '*') {
            begin = true
            i += 3
            continue
        }
        if (!begin) {
            i++
            continue
        }

        if (c == '\n' && isContent && content.isNotBlank()) content.append(c)
        if (c == '\n' || isLineBegin && c == ' ') {
            isLineBegin = true
            i++
            continue
        } else if (isLineBegin && c == '*' && doc[i + 1] == '/') {
            break
        } else if (isLineBegin && c == '*') {
            isLineBegin = false
            isTrimStarting = true
            i++
            continue
        } else if (isTrimStarting && c == ' ') {
            i++
            continue
        } else if (isTrimStarting && c == '@') {
            if (droppedFirstEmptyProperty) saveProperty()
            droppedFirstEmptyProperty = true
            isTrimStarting = false
            isContent = false
            isPropertyKey = true
            i++
            continue
        } else {
            isLineBegin = false
            isTrimStarting = false
        }

        if (isPropertyKey && c == ' ') {
            isPropertyKey = false
        } else if (isPropertyKey) {
            propertyKey.append(c)
        } else if (!isContent) {
            propertyValue.append(c)
        } else if (content.isNotBlank() || c != ' ') {
            content.append(c)
        }
        i++
    }

    saveProperty()
    return JsDoc(content.toString().takeIf { it.isNotBlank() }?.trimEnd(), properties)
}
