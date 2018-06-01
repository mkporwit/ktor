package io.ktor.client.features.json

import io.ktor.client.response.*
import io.ktor.http.content.*
import kotlin.reflect.*

interface JsonSerializer {
    fun write(data: Any): OutgoingContent
    suspend fun read(type: KClass<*>, response: HttpResponse): Any
}