package com.syafii.core.domain.repository

import com.syafii.core.domain.model.Greeting

interface GreetingRepository {
    fun fetchGreeting(): Greeting
}