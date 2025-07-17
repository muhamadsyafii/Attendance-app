package com.syafii.core.domain.usecase.register

import com.syafii.core.domain.model.RegisterRequest
import com.syafii.core.domain.repository.UserRepository
import com.syafii.core.utils.PasswordHasher

class RegisterUseCase(
    private val userRepo: UserRepository
) {
    class RegistrationException(message: String): Exception(message)

    suspend operator fun invoke(req: RegisterRequest) {
        if (userRepo.findByUsername(req.username) != null) {
            throw RegistrationException("Username '${req.username}' sudah terdaftar")
        }
        val hash = PasswordHasher.hash(req.password)
        userRepo.create(req.username, hash, req.role ?: "user")
    }
}