package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.data.api.HackerNewsApi
import com.example.data.source.news.HackerNewsDataSourceImpl
import com.example.data.source.news.local.HackerNewsLocalDataSource
import com.example.data.source.news.remote.HackerNewsRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {
    @Provides
    fun provideHackerNewsRemoteDataSource(hackerNewsApi: HackerNewsApi): HackerNewsRemoteDataSource {
        return HackerNewsRemoteDataSource(hackerNewsApi)
    }

    @Provides
    fun provideHackerNewsLocalDataSource(): HackerNewsLocalDataSource {
        return HackerNewsLocalDataSource()
    }

    @Provides
    fun provideHackerNewsDataSource(
        hackerNewsLocalDataSource: HackerNewsLocalDataSource,
        hackerNewsRemoteDataSource: HackerNewsRemoteDataSource
    ): HackerNewsDataSourceImpl {
        return HackerNewsDataSourceImpl(hackerNewsLocalDataSource, hackerNewsRemoteDataSource)
    }
}