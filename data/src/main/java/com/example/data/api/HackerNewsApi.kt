package com.example.data.api

import com.example.data.entities.DataEntity
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HackerNewsApi {

    @GET("/news")
    fun getHackerNews(@Query("page") page: Int): Single<List<DataEntity.HackerNews>>

    @GET("/news")
    fun getHackerNewsOB(@Query("page") page: Int): Observable<List<DataEntity.HackerNews>>
}