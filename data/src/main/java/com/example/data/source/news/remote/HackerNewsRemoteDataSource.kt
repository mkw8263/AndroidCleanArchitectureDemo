package com.example.data.source.news.remote

import com.example.data.api.HackerNewsApi
import com.example.data.source.news.HackerNewsDataSource
import com.example.domain.entity.Entity
import io.reactivex.Observable
import io.reactivex.Single

class HackerNewsRemoteDataSource(private val hackerNewsApi: HackerNewsApi) :
    HackerNewsDataSource {
    override fun getHackerNewsList(page: Int): Single<List<Entity.HackerNews>> {
        return hackerNewsApi.getHackerNews(page)
    }

    override fun getHackerNewsListOB(page: Int): Observable<List<Entity.HackerNews>> {
        return hackerNewsApi.getHackerNewsOB(page)
    }
}