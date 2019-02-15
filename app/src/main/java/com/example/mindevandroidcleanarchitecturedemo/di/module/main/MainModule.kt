//package com.example.mindevandroidcleanarchitecturedemo.di.module.main
////
////import com.example.data.repository.HackerNewsRepositoryImpl
////import com.example.data.source.news.HackerNewsDataSource
////import com.example.data.source.news.HackerNewsDataSourceImpl
////import com.example.data.source.news.local.HackerNewsLocalDataSource
////import com.example.data.source.news.remote.HackerNewsRemoteDataSource
////import com.example.domain.HackerNewsRepository
////import com.example.mindevandroidcleanarchitecturedemo.di.module.NetWorkModule
////import dagger.Module
////import dagger.Provides
////
////@Module(includes = [NetWorkModule::class])
////class MainModule {
////    @Provides
////    fun provideDataSource(
////        hackerNewsLocalDataSource: HackerNewsLocalDataSource,
////        hackerNewsRemoteDataSource: HackerNewsRemoteDataSource
////    ): HackerNewsDataSource {
////        return HackerNewsDataSourceImpl(hackerNewsLocalDataSource, hackerNewsRemoteDataSource)
////    }
////
////    @Provides
////    fun provideRepository(hackerNewsDataSource: HackerNewsDataSource): HackerNewsRepository {
////        return HackerNewsRepositoryImpl(hackerNewsDataSource)
////    }
////
//////    @Provides
//////    fun provideUseCase(hackerNewsRepository: HackerNewsRepository): HackerNewsUseCase {
//////        return HackerNewsUseCaseImpl(hackerNewsRepository)
//////    }
////}