package com.example.data.source.news.remote

import com.example.data.api.HackerNewsApi
import com.example.data.source.news.HackerNewsDataSource
import com.example.domain.Result
import com.example.domain.entity.Entity
import io.reactivex.Single

class HackerNewsRemoteDataSource(private val hackerNewsApi: HackerNewsApi) :
    HackerNewsDataSource {
    override fun getHackerNewsList(page: Int):  Single<Result<List<Entity.HackerNews>>> {
        return hackerNewsApi.getHackerNews(page)
    }
}