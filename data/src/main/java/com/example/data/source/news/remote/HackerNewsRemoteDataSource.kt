package com.example.data.source.news.remote

import com.example.data.api.HackerNewsApi
import com.example.data.entities.DataEntity
import com.example.data.source.news.HackerNewsDataSource
import io.reactivex.Observable
import io.reactivex.Single

class HackerNewsRemoteDataSource(private val hackerNewsApi: HackerNewsApi) :
    HackerNewsDataSource {
    override fun getHackerNewsList(page: Int): Single<List<DataEntity.HackerNews>> {
        return hackerNewsApi.getHackerNews(page)
    }

    override fun getHackerNewsListOB(page: Int): Observable<List<DataEntity.HackerNews>> {
        return hackerNewsApi.getHackerNewsOB(page)
    }
}