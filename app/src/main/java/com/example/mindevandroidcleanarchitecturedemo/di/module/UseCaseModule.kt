package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.data.repository.HackerNewsRepositoryImpl
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.domain.usecase.news.HackerNewsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideUseCase(repositoryImpl: HackerNewsRepositoryImpl): HackerNewsUseCase {
        return HackerNewsUseCaseImpl(repositoryImpl)
    }
}