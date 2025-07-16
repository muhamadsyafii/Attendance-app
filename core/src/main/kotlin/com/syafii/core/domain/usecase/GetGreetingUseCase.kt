package com.syafii.core.domain.usecase

import com.syafii.core.domain.model.Greeting

interface GetGreetingUseCase {
    fun execute(): Greeting
}