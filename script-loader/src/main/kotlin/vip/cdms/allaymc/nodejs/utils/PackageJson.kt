package vip.cdms.allaymc.nodejs.utils

import kotlinx.serialization.json.*

@Suppress("OPT_IN_USAGE")
private val PackageJsonParser = Json {
    allowComments = true
    allowTrailingComma = true
}

class PackageJson(val raw: JsonObject) {
    val name get() = raw["name"]?.jsonPrimitive?.content
    val version get() = raw["version"]?.jsonPrimitive?.content
    val description get() = raw["description"]?.jsonPrimitive?.content
    val main get() = raw["main"]?.jsonPrimitive?.content

//    val dependencies get() = raw["dependencies"]?.jsonObject?.toMap()?.mapValues { it.value.jsonPrimitive.content }
    val allayDependencies get() = raw["allayDependencies"]?.jsonObject?.toMap()?.mapValues { it.value.jsonPrimitive.content }

    val author = raw["author"]
    fun getAuthors(author: JsonElement? = this.author): MutableList<String>? = when (author) {
        is JsonPrimitive -> mutableListOf(author.content)
        is JsonArray -> author.flatMap { getAuthors(it) ?: listOf() }.toMutableList()
        is JsonObject -> author["name"]?.let { mutableListOf(it.jsonPrimitive.content) }
        else -> null
    }

    val homepage get() = raw["homepage"]?.jsonPrimitive?.content

    companion object {
        fun parseFrom(json: String) =
            PackageJson(PackageJsonParser.parseToJsonElement(json.takeIf { it.isNotBlank() } ?: "{}").jsonObject)
    }
}
