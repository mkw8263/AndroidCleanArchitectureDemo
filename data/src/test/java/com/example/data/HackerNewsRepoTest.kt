package com.example.data

import com.example.data.api.HackerNewsApi
import com.example.data.entities.DataEntity
import com.example.data.mapper.DataHackerNewsMapper
import com.example.domain.entities.DomainEntity
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder

class HackerNewsRepoTest {
    private var hackerNewsApi = providesRetrofit()
    private lateinit var dataEntity: List<DataEntity.HackerNews>
    private val dataHackerNewsMapper = DataHackerNewsMapper()

    @Before
    fun setup() {
        dataEntity = Gson().fromJson(
            fromResourceJsonFile(), Array<DataEntity.HackerNews>::class.java
        ).toList()

    }

    @Test
    fun `check, remote data values`() {
        val response = hackerNewsApi.getHackerNews(3)
            .blockingGet()

        for (index in 0 until response.size) {
            Assert.assertEquals(dataEntity[index].id, response[index].id)
            Assert.assertEquals(dataEntity[index].comments_count, response[index].comments_count)
            Assert.assertEquals(dataEntity[index].domain, response[index].domain)
            Assert.assertEquals(dataEntity[index].title, response[index].title)
            Assert.assertEquals(dataEntity[index].time, response[index].time)
            Assert.assertEquals(dataEntity[index].time_ago, response[index].time_ago)
            Assert.assertEquals(dataEntity[index].type, response[index].type)
            Assert.assertEquals(dataEntity[index].url, response[index].url)
            Assert.assertEquals(dataEntity[index].points, response[index].points)
        }
    }

    @Test
    fun `check, DataEntity change DomainEntity`() {
        val domainEntityListA = dataEntity.map {
            with(it) {
                DomainEntity.HackerNews(comments_count, id, time_ago, title)
            }
        }

        val domainEntityListB = dataHackerNewsMapper.mapFromEntity(dataEntity)
        Assert.assertTrue(domainEntityListA == domainEntityListB)
    }

    private fun okHttpClient(): OkHttpClient.Builder {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val response: Response = chain.proceed(chain.request().newBuilder().build())
            val content = URLDecoder.decode(fromResourceJsonFile(), "utf-8")
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
    }
}