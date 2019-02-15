package com.example.data.source.news

import com.example.data.source.news.local.HackerNewsLocalDataSource
import com.example.data.source.news.remote.HackerNewsRemoteDataSource
import com.example.domain.entity.Entity
import io.reactivex.Single

class HackerNewsDataSourceImpl(
    val hackerNewsLocalDataSource: HackerNewsLocalDataSource,
    val hackerNewsRemoteDataSource: HackerNewsRemoteDataSource
) : HackerNewsDataSource {
    override fun getHackerNewsList(page: Int): Single<List<Entity.HackerNews>> {
        return hackerNewsRemoteDataSource.getHackerNewsList(page)
    }
}