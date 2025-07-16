package com.syafii.data.repository

import com.syafii.core.domain.model.Greeting
import com.syafii.core.domain.repository.GreetingRepository

class GreetingRepositoryImpl : GreetingRepository {
    override fun fetchGreeting(): Greeting = Greeting("Hello World!")
}