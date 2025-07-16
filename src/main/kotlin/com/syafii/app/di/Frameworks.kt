package com.syafii.app.di

import com.syafii.core.domain.model.JwtConfig
import com.syafii.core.domain.repository.GreetingRepository
import com.syafii.core.domain.repository.UserRepository
import com.syafii.core.domain.usecase.GetGreetingUseCase
import com.syafii.core.domain.usecase.GetGreetingUseCaseImpl
import com.syafii.core.domain.usecase.login.LoginUseCase
import com.syafii.data.repository.GreetingRepositoryImpl
import com.syafii.data.repository.UserRepositoryImpl
import io.ktor.server.application.Application
import org.koin.dsl.module


val koinModule = module {
    single<GreetingRepository> { GreetingRepositoryImpl() }
    single<GetGreetingUseCase> { GetGreetingUseCaseImpl(get()) }
    single<UserRepository> { UserRepositoryImpl() }
    single { LoginUseCase(get(), get()) }

/*    single {
        val env = get<Application>().environment
        val jwtConfig = env.config.config("jwt")
        JwtConfig(
            secret = jwtConfig.property("secret").getString(),
            issuer = jwtConfig.property("issuer").getString(),
            audience = jwtConfig.property("audience").getString()
        )
    }*/

}