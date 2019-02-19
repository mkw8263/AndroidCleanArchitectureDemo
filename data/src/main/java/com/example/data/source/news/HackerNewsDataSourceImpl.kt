package com.example.data.source.news

import com.example.data.entities.DataEntity
import com.example.data.source.news.local.HackerNewsLocalDataSource
import com.example.data.source.news.remote.HackerNewsRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single

class HackerNewsDataSourceImpl(
    private val hackerNewsLocalDataSource: HackerNewsLocalDataSource,
    private val hackerNewsRemoteDataSource: HackerNewsRemoteDataSource
) : HackerNewsDataSource {
    override fun getHackerNewsList(page: Int): Single<List<DataEntity.HackerNews>> {
        return hackerNewsRemoteDataSource.getHackerNewsList(page)
    }

    override fun getHackerNewsListOB(page: Int): Observable<List<DataEntity.HackerNews>> {
        return hackerNewsRemoteDataSource.getHackerNewsListOB(page)
    }
}