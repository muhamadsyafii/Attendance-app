package com.syafii.app.di

import com.syafii.core.domain.repository.GreetingRepository
import com.syafii.core.domain.usecase.GetGreetingUseCase
import com.syafii.core.domain.usecase.GetGreetingUseCaseImpl
import com.syafii.data.repository.GreetingRepositoryImpl
import org.koin.dsl.module


val koinModule = module {
    single<GreetingRepository> {
        GreetingRepositoryImpl() }
    single<GetGreetingUseCase> { GetGreetingUseCaseImpl(get()) }
}