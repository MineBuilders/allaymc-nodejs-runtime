package vip.cdms.allaymc.nodejs.utils

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

operator fun JsonObject?.plus(other: JsonObject?): JsonObject = buildJsonObject {
    this@plus?.forEach { (key, value) -> put(key, value) }
    other?.forEach { (key, value) -> put(key, value) }
}
