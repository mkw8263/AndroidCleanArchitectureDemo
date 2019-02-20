package com.example.data.source.news.remote

import com.example.data.api.HackerNewsApi
import com.example.data.entities.DataEntity
import io.reactivex.Observable
import io.reactivex.Single

open class HackerNewsRemoteDataSource(private val hackerNewsApi: HackerNewsApi) {
    open fun getHackerNewsList(page: Int): Single<List<DataEntity.HackerNews>> {
        return hackerNewsApi.getHackerNews(page)
    }

    open fun getHackerNewsListOB(page: Int): Observable<List<DataEntity.HackerNews>> {
        return hackerNewsApi.getHackerNewsOB(page)
    }
}