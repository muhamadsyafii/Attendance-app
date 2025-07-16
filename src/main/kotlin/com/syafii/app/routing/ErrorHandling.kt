package com.syafii.app.routing

import io.ktor.client.utils.EmptyContent.status
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.request.contentType
import io.ktor.server.request.httpMethod
import io.ktor.server.request.uri
import io.ktor.server.response.*
import io.ktor.util.AttributeKey
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val success: Boolean,
    val messageTitle: String,
    val message: String,
    val responseTime: String,
    val data: T?
)

class ApiException(
    val status: HttpStatusCode,
    val title: String,
    override val message: String
) : RuntimeException(message)

fun Application.installTiming() {
    intercept(ApplicationCallPipeline.Setup) {
        call.attributes.put(AttributeKey<Long>("startTime"), System.currentTimeMillis())
    }
}

fun Application.installErrorHandling() {
    install(StatusPages) {
        // Tangani ApiException
        exception<ApiException> { call, cause ->
            val start = call.attributes[AttributeKey<Long>("startTime")]
            val elapsed = System.currentTimeMillis() - start
            call.respond(
                cause.status,
                ApiResponse<Unit>(
                    success = false,
                    messageTitle = cause.title,
                    message = cause.message,
                    responseTime = "$elapsed ms.",
                    data = null
                )
            )
        }

        // 405 Method Not Allowed
        status(HttpStatusCode.MethodNotAllowed) { call, status ->
            val start = call.attributes[AttributeKey<Long>("startTime")]
            val elapsed = System.currentTimeMillis() - start
            call.respond(
                status,
                ApiResponse<Unit>(
                    success = false,
                    messageTitle = "Method Not Allowed",
                    message = "${call.request.httpMethod.value} tidak diizinkan di ${call.request.uri}",
                    responseTime = "$elapsed ms.",
                    data = null
                )
            )
        }

        // 415 Unsupported Media Type
        status(HttpStatusCode.UnsupportedMediaType) { call, status ->
            val start = call.attributes[AttributeKey<Long>("startTime")]
            val elapsed = System.currentTimeMillis() - start
            call.respond(
                status,
                ApiResponse<Unit>(
                    success = false,
                    messageTitle = "Unsupported Media Type",
                    message = "Content-Type ${call.request.contentType()} tidak didukung",
                    responseTime = "$elapsed ms.",
                    data = null
                )
            )
        }

        // 400 Bad Request (default untuk 4xx lain-lain)
        status(HttpStatusCode.BadRequest) { call, status ->
            val start = call.attributes[AttributeKey<Long>("startTime")]
            val elapsed = System.currentTimeMillis() - start
            call.respond(
                status,
                ApiResponse<Unit>(
                    success = false,
                    messageTitle = "Invalid data",
                    message = "Request tidak valid",
                    responseTime = "$elapsed ms.",
                    data = null
                )
            )
        }

        // 500 Internal Server Error
        exception<Throwable> { call, cause ->
            call.application.log.error("Unhandled exception", cause)
            val start = call.attributes[AttributeKey<Long>("startTime")]
            val elapsed = System.currentTimeMillis() - start
            call.respond(
                HttpStatusCode.InternalServerError,
                ApiResponse<Unit>(
                    success = false,
                    messageTitle = "Internal Server Error",
                    message = "Terjadi kesalahan di server",
                    responseTime = "$elapsed ms.",
                    data = null
                )
            )
        }
    }
}