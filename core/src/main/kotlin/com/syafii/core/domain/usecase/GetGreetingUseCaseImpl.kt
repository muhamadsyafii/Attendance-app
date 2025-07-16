package com.syafii.core.domain.usecase

import com.syafii.core.domain.model.Greeting
import com.syafii.core.domain.repository.GreetingRepository

class GetGreetingUseCaseImpl(
    private val repository: GreetingRepository
) : GetGreetingUseCase {
    override fun execute(): Greeting = repository.fetchGreeting()
}