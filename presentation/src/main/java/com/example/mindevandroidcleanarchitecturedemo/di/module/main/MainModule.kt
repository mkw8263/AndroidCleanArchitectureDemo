package com.example.mindevandroidcleanarchitecturedemo.di.module.main

import com.example.data.api.HackerNewsApi
import com.example.data.mapper.DataHackerNewsMapper
import com.example.data.repository.HackerNewsRepositoryImpl
import com.example.data.source.news.local.HackerNewsLocalDataSource
import com.example.data.source.news.remote.HackerNewsRemoteDataSource
import com.example.domain.HackerNewsRepository
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.di.module.MainViewModelFactory
import com.example.mindevandroidcleanarchitecturedemo.di.qualifier.PerActivity
import com.example.mindevandroidcleanarchitecturedemo.mapper.PresentationHackerNewsMapper
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MainModule {

    @Provides
    @PerActivity
    @Named("HackerNewsRepositoryImpl")
    fun provideRepository(
        hackerNewsLocalDataSource: HackerNewsLocalDataSource,
        hackerNewsRemoteDataSource: HackerNewsRemoteDataSource,
        dataHackerNewsMapper: DataHackerNewsMapper
    ): HackerNewsRepository {
        return HackerNewsRepositoryImpl(hackerNewsLocalDataSource, hackerNewsRemoteDataSource, dataHackerNewsMapper)
    }

    @Provides
    @PerActivity
    fun provideHackerNewsRemoteDataSource(hackerNewsApi: HackerNewsApi): HackerNewsRemoteDataSource {
        return HackerNewsRemoteDataSource(hackerNewsApi)
    }

    @Provides
    @PerActivity
    fun provideHackerNewsUseCase(@Named("HackerNewsRepositoryImpl") hackerNewsRepository: HackerNewsRepository): HackerNewsUseCase {
        return HackerNewsUseCase(hackerNewsRepository)
    }

    @Provides
    @PerActivity
    fun provideMainViewModelFactory(
        hackerNewsUseCase: HackerNewsUseCase,
        presentationHackerNewsMapper: PresentationHackerNewsMapper
    ): MainViewModelFactory {
        return MainViewModelFactory(hackerNewsUseCase, presentationHackerNewsMapper)
    }
}