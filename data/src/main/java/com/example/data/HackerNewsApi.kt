package com.example.data

import com.example.domain.entity.Entity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsApi {

    @GET("/news")
    fun getHackerNews(@Query("page") page: Int): Single<Entity.HackerNews>
}