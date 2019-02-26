package com.example.data

import com.example.data.api.HackerNewsApi
import com.example.data.entities.DataEntity
import com.google.gson.Gson
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HackerNewsRepoTest {
    private var hackerNewsApi = providesRetrofit()

    @Test
    fun `check, remote data values`() {
        val reviews = Gson().fromJson(
            javaClass.classLoader!!.getResourceAsStream("GetHackerNews.json").bufferedReader().use { it.readText() },
            Array<DataEntity.HackerNews>::class.java
        )
        reviews.forEach { data ->
            with(data) {
                println("comments_count: $comments_count ")
                println("id: $id ")
                println("points: $points ")
                println("time: $time ")
                println("time_ago: $time_ago ")
                println("title: $title ")
                println("type: $type ")
                println("url: $url ")
                println("user: $user ")
            }
        }
    }

    @Test
    fun `check, DataEntity change DomainEntity`() {

    }

    @Test
    fun `check, repository data`() {

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