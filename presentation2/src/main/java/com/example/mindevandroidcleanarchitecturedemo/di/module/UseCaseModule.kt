package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.domain.HackerNewsRepository
import com.example.domain.usecase.news.HackerNewsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UseCaseModule {
    @Provides
    fun provideUseCase(@Named("HackerNewsRepositoryImpl") hackerNewsRepository: HackerNewsRepository): HackerNewsUseCase {
        return HackerNewsUseCase(hackerNewsRepository)
    }
}