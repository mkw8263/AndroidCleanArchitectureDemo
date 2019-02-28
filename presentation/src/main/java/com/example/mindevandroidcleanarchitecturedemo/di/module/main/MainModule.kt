package com.example.mindevandroidcleanarchitecturedemo.di.module.main

import com.example.data.api.HackerNewsApi
import com.example.data.mapper.DataHackerNewsMapper
import com.example.data.repository.HackerNewsRepositoryImpl
import com.example.data.source.news.local.HackerNewsLocalData
import com.example.data.source.news.remote.HackerNewsRemoteData
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
        hackerNewsLocalData: HackerNewsLocalData,
        hackerNewsRemoteData: HackerNewsRemoteData,
        dataHackerNewsMapper: DataHackerNewsMapper
    ): HackerNewsRepository {
        return HackerNewsRepositoryImpl(hackerNewsLocalData, hackerNewsRemoteData, dataHackerNewsMapper)
    }

    @Provides
    @PerActivity
    fun provideHackerNewsRemoteDataSource(hackerNewsApi: HackerNewsApi): HackerNewsRemoteData {
        return HackerNewsRemoteData(hackerNewsApi)
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