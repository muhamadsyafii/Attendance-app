package com.syafii.core.domain.usecase.login

import com.syafii.core.domain.model.JwtConfig
import com.syafii.core.domain.repository.UserRepository
import com.syafii.core.domain.request.LoginRequest
import com.syafii.core.utils.PasswordHasher

class LoginUseCase(
    private val userRepo: UserRepository,
    private val jwtConfig: JwtConfig
) {
    class AuthException(message: String): Exception(message)

    suspend operator fun invoke(req: LoginRequest): String {
        val user = userRepo.findByUsername(req.username)
            ?: throw AuthException("Invalid username or password")
        if (!PasswordHasher.verify(req.password, user.passwordHash))
            throw AuthException("Invalid username or password")
        return jwtConfig.generateToken(user.id, user.username)
    }
}