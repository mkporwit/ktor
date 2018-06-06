package io.ktor.client.features

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.response.*
import io.ktor.http.*
import io.ktor.http.content.*
import kotlinx.coroutines.experimental.io.*
import kotlinx.io.core.*

fun HttpClient.defaultTransformers() {
    requestPipeline.intercept(HttpRequestPipeline.Render) { body ->
        when (body) {
            is ByteArray -> proceedWith(object : OutgoingContent.ByteArrayContent() {
                override val contentLength: Long = body.size.toLong()
                override fun bytes(): ByteArray = body
            })
        }
    }

    responsePipeline.intercept(HttpResponsePipeline.Parse) { (type, response) ->
        if (response !is HttpResponse) return@intercept
        val contentLength = response.headers[HttpHeaders.ContentLength]?.toLong() ?: Long.MAX_VALUE

        when (type) {
            ByteArray::class -> {
                val readRemaining = response.content.readRemaining(contentLength)
                proceedWith(HttpResponseContainer(type, readRemaining.readBytes()))
            }
        }
    }
}
