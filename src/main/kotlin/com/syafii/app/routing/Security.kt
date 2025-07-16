package com.syafii.app.routing


import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt


fun Application.installSecurity(jwtVerifier: JWTVerifier) {
    install(Authentication) {
        jwt("auth-jwt") {
            verifier(jwtVerifier)
            realm = "attendance-app"
            validate { credential ->
                if (credential.payload.subject != null) JWTPrincipal(credential.payload) else null
            }
        }
    }
}