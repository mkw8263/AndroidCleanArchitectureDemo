package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.data.api.HackerNewsApi
import com.example.data.source.remote.HackerNewsRemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {
    @Provides
    fun provideHackerNewsRemoteDataSource(hackerNewsApi: HackerNewsApi): HackerNewsRemoteDataSource {
        return HackerNewsRemoteDataSource(hackerNewsApi)
    }
}