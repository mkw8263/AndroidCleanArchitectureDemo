package com.example.mindevandroidcleanarchitecturedemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.api.HackerNewsApi
import com.example.data.entities.DataEntity
import com.example.data.mapper.DataHackerNewsMapper
import com.example.data.repository.HackerNewsRepositoryImpl
import com.example.data.source.news.local.HackerNewsLocalData
import com.example.data.source.news.remote.HackerNewsRemoteData
import com.example.domain.entities.DomainEntity
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity
import com.example.mindevandroidcleanarchitecturedemo.mapper.PresentationHackerNewsMapper
import com.example.mindevandroidcleanarchitecturedemo.vm.MainViewModel
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder

class HackerNewsVMTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mainViewModel: MainViewModel

    private val presentationHackerNewsMapper = PresentationHackerNewsMapper()
    private lateinit var domainEntity: List<DomainEntity.HackerNews>
    private lateinit var presentationEntity: List<PresentationEntity.HackerNews>

    @Before
    fun setUp() {
        domainEntity = Gson().fromJson(fromResourceJsonFile(), Array<DataEntity.HackerNews>::class.java)
            .map { dataEntity ->
                with(dataEntity) {
                    DomainEntity.HackerNews(comments_count, id, time_ago, title)
                }
            }
        presentationEntity = domainEntity.map {
            presentationHackerNewsMapper.mapToView(it)
        }

        val hackerNewsRepositoryImpl = HackerNewsRepositoryImpl(
            HackerNewsLocalData(),
            HackerNewsRemoteData(providesRetrofit()),
            DataHackerNewsMapper()
        )

        val hackerNewsUseCase = HackerNewsUseCase(hackerNewsRepositoryImpl)
        mainViewModel = MainViewModel(hackerNewsUseCase, PresentationHackerNewsMapper())
    }

    @Test
    fun `live data, hacker news success`() {
        mainViewModel.mutableLiveResult
            .postValue(MainViewModel.Result.NewsData(presentationEntity))

        assertEquals(presentationEntity, (mainViewModel.liveResult.value as MainViewModel.Result.NewsData).data)
    }

    @Test
    fun `live data, hacker news error`() {
        val value = Throwable(" error ")
        mainViewModel.mutableLiveResult
            .postValue(MainViewModel.Result.ShowError(value))

        assertEquals(value, (mainViewModel.liveResult.value as MainViewModel.Result.ShowError).throwable)
    }

    @Test
    fun `live data, hacker news loading`() {
        mainViewModel.mutableLiveResult
            .postValue(MainViewModel.Result.ProgressBarVisibility(true))

        mainViewModel.mutableLiveResult
            .postValue(MainViewModel.Result.ProgressBarVisibility(false))

        assertEquals(false, (mainViewModel.liveResult.value as MainViewModel.Result.ProgressBarVisibility).isLoading)
    }

    @Test
    fun `ui entity, DomainEntity change Presentation Entity`() {
        for (index in 0 until presentationEntity.size) {
            print("${domainEntity[index].comments_count} vs ${presentationEntity[index].comments_count}\n")
            assertFalse(domainEntity[index].comments_count == presentationEntity[index].comments_count)
            assertFalse(domainEntity[index].id == presentationEntity[index].id)
            assertFalse(domainEntity[index].time_ago == presentationEntity[index].time_ago)
            assertFalse(domainEntity[index].title == presentationEntity[index].title)
        }
    }

    private fun okHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val response: Response = chain.proceed(chain.request().newBuilder().build())
            val content = URLDecoder.decode(fromResourceJsonFile(), UTF_8)
            val responseBody = ResponseBody.create(response.body()?.contentType(), content)

            response.newBuilder()
                .body(responseBody)
                .build()
        }
    }

    private fun fromResourceJsonFile(): String {
        return javaClass.classLoader!!.getResourceAsStream(resource)
            .bufferedReader()
            .use {
                it.readText()
            }
    }

    private fun providesRetrofit(): HackerNewsApi {
        return Retrofit.Builder()
            .baseUrl("http://api.hackerwebapp.com/")
            .client(okHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(HackerNewsApi::class.java)
    }

    companion object {
        const val resource: String = "GetHackerNews.json"
        const val UTF_8 = "utf-8"
    }
}