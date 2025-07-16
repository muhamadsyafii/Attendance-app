package com.syafii.app.routing

import com.syafii.core.domain.model.Greeting
import com.syafii.core.domain.usecase.GetGreetingUseCase
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import org.koin.ktor.ext.getKoin

fun Routing.greetingRoutes() {
    val getGreetingUseCase: GetGreetingUseCase = application.getKoin().get()
    get("/") {
        val greeting: Greeting = getGreetingUseCase.execute()
        call.respond(greeting)
    }
}