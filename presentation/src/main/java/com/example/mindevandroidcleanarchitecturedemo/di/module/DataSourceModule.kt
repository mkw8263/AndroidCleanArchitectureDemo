package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.data.api.HackerNewsApi
import com.example.data.source.news.HackerNewsDataSource
import com.example.data.source.news.HackerNewsDataSourceImpl
import com.example.data.source.news.local.HackerNewsLocalDataSource
import com.example.data.source.news.remote.HackerNewsRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DataSourceModule {
    @Provides
    fun provideHackerNewsRemoteDataSource(hackerNewsApi: HackerNewsApi): HackerNewsRemoteDataSource {
        return HackerNewsRemoteDataSource(hackerNewsApi)
    }

    @Provides
    @Named("HackerNewsDataSourceImpl")
    fun provideHackerNewsDataSource(
        hackerNewsLocalDataSource: HackerNewsLocalDataSource,
        hackerNewsRemoteDataSource: HackerNewsRemoteDataSource
    ): HackerNewsDataSource {
        return HackerNewsDataSourceImpl(hackerNewsLocalDataSource, hackerNewsRemoteDataSource)
    }
}