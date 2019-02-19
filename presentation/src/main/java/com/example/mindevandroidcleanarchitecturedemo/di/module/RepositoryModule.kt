package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.data.mapper.DataHackerNewsMapper
import com.example.data.repository.HackerNewsRepositoryImpl
import com.example.data.source.news.local.HackerNewsLocalDataSource
import com.example.data.source.news.remote.HackerNewsRemoteDataSource
import com.example.domain.HackerNewsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class RepositoryModule {
    @Provides
    @Named("HackerNewsRepositoryImpl")
    fun provideRepository(
        hackerNewsLocalDataSource: HackerNewsLocalDataSource,
        hackerNewsRemoteDataSource: HackerNewsRemoteDataSource
        , dataHackerNewsMapper: DataHackerNewsMapper
    ): HackerNewsRepository {
        return HackerNewsRepositoryImpl(hackerNewsLocalDataSource, hackerNewsRemoteDataSource, dataHackerNewsMapper)
    }
}