package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.data.repository.HackerNewsRepositoryImpl
import com.example.data.source.news.HackerNewsDataSourceImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(hackerNewsDataSourceImpl: HackerNewsDataSourceImpl): HackerNewsRepositoryImpl {
        return HackerNewsRepositoryImpl(hackerNewsDataSourceImpl)
    }
}