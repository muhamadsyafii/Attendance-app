package com.syafii.core.domain.model



import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import java.util.Date

class JwtConfig(
    secret: String,
    private val issuer: String,
    private val audience: String
) {
    private val algorithm = Algorithm.HMAC256(secret)
    val verifier: JWTVerifier = JWT.require(algorithm)
        .withIssuer(issuer)
        .withAudience(audience)
        .build()

    fun generateToken(userId: Int, username: String): String {
        val now = System.currentTimeMillis()
        return JWT.create()
            .withIssuer(issuer)
            .withAudience(audience)
            .withSubject(userId.toString())
            .withClaim("username", username)
            .withExpiresAt(Date(now + 3_600_000)) // 1 jam
            .sign(algorithm)
    }
}
