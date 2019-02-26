package com.example.data

import com.example.data.api.HackerNewsApi
import com.example.data.entities.DataEntity
import com.example.data.mapper.DataHackerNewsMapper
import com.example.data.repository.HackerNewsRepositoryImpl
import com.example.data.source.news.remote.HackerNewsRemoteDataSource
import com.example.domain.entities.DomainEntity
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HackerNewsRepoTest {
    private var hackerNewsApi = providesRetrofit()

    @Test
    fun `check, remote data values`() {
        val list: MutableList<DataEntity.HackerNews> = mutableListOf()
        list.apply {
            add(DataEntity.HackerNews(0, "1", 0, 0, 0, "1", "1", "1", "1", "1"))
            add(DataEntity.HackerNews(0, "1", 0, 0, 0, "1", "1", "1", "1", "1"))
            add(DataEntity.HackerNews(0, "1", 0, 0, 0, "1", "1", "1", "1", "1"))
            add(DataEntity.HackerNews(0, "1", 0, 0, 0, "1", "1", "1", "1", "1"))
        }

        val hackerNewsRemoteDataSource: HackerNewsRemoteDataSource = mock()
        given { hackerNewsRemoteDataSource.getHackerNewsList(3) }.willReturn(Single.just(list))

        hackerNewsRemoteDataSource.getHackerNewsList(3)
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(list)

        verify(hackerNewsRemoteDataSource).getHackerNewsList(3)
    }

    @Test
    fun `check, DataEntity change DomainEntity`() {
        val list: ArrayList<DataEntity.HackerNews> = ArrayList()
        list.apply {
            add(DataEntity.HackerNews(0, "1", 0, 0, 0, "1", "1", "1", "1", "1"))
            add(DataEntity.HackerNews(0, "1", 0, 0, 0, "1", "1", "1", "1", "1"))
            add(DataEntity.HackerNews(0, "1", 0, 0, 0, "1", "1", "1", "1", "1"))
            add(DataEntity.HackerNews(0, "1", 0, 0, 0, "1", "1", "1", "1", "1"))
        }

        val hackerNewsRemoteDataSource: HackerNewsRemoteDataSource = mock()
        given { hackerNewsRemoteDataSource.getHackerNewsList(3) }.willReturn(Single.just(list))

        val list2: ArrayList<DomainEntity.HackerNews> = ArrayList()
        list2.apply {
            add(DomainEntity.HackerNews(0, 0, "1", "1"))
            add(DomainEntity.HackerNews(0, 0, "1", "1"))
            add(DomainEntity.HackerNews(0, 0, "1", "1"))
            add(DomainEntity.HackerNews(0, 0, "1", "1"))
        }

        val hackerNewsMapper = DataHackerNewsMapper()
        hackerNewsRemoteDataSource.getHackerNewsList(3)
            .map { hackerNewsMapper.mapFromEntity(it) }
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(list2)

        verify(hackerNewsRemoteDataSource).getHackerNewsList(3)
    }

    @Test
    fun `check, repository data`() {
        val list: MutableList<DomainEntity.HackerNews> = mutableListOf()
        list.apply {
            add(DomainEntity.HackerNews(0, 0, "1"))
            add(DomainEntity.HackerNews(0, 0, "1"))
            add(DomainEntity.HackerNews(0, 0, "1"))
            add(DomainEntity.HackerNews(0, 0, "1"))
        }

        val hackerNewsRepositoryImpl: HackerNewsRepositoryImpl = mock()
        given { hackerNewsRepositoryImpl.getHackerNewsList(3) }.willReturn(Single.just(list))
        hackerNewsRepositoryImpl.getHackerNewsList(3)
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertValue(list)

        verify(hackerNewsRepositoryImpl).getHackerNewsList(3)
    }

    @Test
    fun `network request`() {
        val result = hackerNewsApi.getHackerNews(3)
            .map { data ->
                data.map { "title: ${it.title}, time_ago: ${it.time_ago}, comments_count: ${it.comments_count}, user: ${it.user}\n" }
            }.blockingGet()
        print(result)
    }

    private fun providesRetrofit(): HackerNewsApi {
        return Retrofit.Builder()
            .baseUrl("http://api.hackerwebapp.com/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HackerNewsApi::class.java)
    }
}