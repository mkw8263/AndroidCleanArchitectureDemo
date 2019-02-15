package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.data.repository.HackerNewsRepositoryImpl
import com.example.data.source.news.HackerNewsDataSourceImpl
import com.example.domain.HackerNewsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(hackerNewsDataSourceImpl: HackerNewsDataSourceImpl): HackerNewsRepository {
        return HackerNewsRepositoryImpl(hackerNewsDataSourceImpl)
    }
}