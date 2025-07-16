package com.syafii.app.api

import com.syafii.app.routing.ApiException
import com.syafii.app.routing.ApiResponse
import com.syafii.core.domain.request.LoginRequest
import com.syafii.core.domain.usecase.login.LoginUseCase
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.application
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.util.AttributeKey
import org.koin.ktor.ext.getKoin

fun Routing.authRoutes() {
    val loginUseCase: LoginUseCase = application.getKoin().get()

    route("/auth") {
        post("/login") {
            val req = call.receive<LoginRequest>()
            val token = try {
                loginUseCase(req)
            } catch (e: LoginUseCase.AuthException) {
                throw ApiException(HttpStatusCode.Unauthorized, "Invalid credentials", e.message ?: "")
            }
            val elapsed = System.currentTimeMillis() - call.attributes[AttributeKey<Long>("startTime")]
            call.respond(
                HttpStatusCode.OK,
                ApiResponse(
                    success = true,
                    messageTitle = "Success",
                    message = "Login Is Success",
                    responseTime = "$elapsed ms.",
                    data = mapOf("token" to token)
                )
            )
        }
    }
}
