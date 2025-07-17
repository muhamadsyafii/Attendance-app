package com.syafii.app.di

import com.syafii.core.domain.repository.GreetingRepository
import com.syafii.core.domain.repository.UserRepository
import com.syafii.core.domain.usecase.GetGreetingUseCase
import com.syafii.core.domain.usecase.GetGreetingUseCaseImpl
import com.syafii.core.domain.usecase.login.LoginUseCase
import com.syafii.core.domain.usecase.register.RegisterUseCase
import com.syafii.data.repository.GreetingRepositoryImpl
import com.syafii.data.repository.UserRepositoryImpl
import org.koin.dsl.module


val koinModule = module {
    single<GreetingRepository> { GreetingRepositoryImpl() }
    single<GetGreetingUseCase> { GetGreetingUseCaseImpl(get()) }
    single<UserRepository> { UserRepositoryImpl() }
    single { LoginUseCase(get(), get()) }
    single { RegisterUseCase(get()) }
}