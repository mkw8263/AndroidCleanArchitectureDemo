package com.example.data.repository

import com.example.data.source.news.HackerNewsDataSource
import com.example.domain.HackerNewsRepository
import com.example.domain.entity.Entity
import io.reactivex.Observable
import io.reactivex.Single

class HackerNewsRepositoryImpl(private val hackerNewsDataSource: HackerNewsDataSource) :
    HackerNewsRepository {
    override fun getHackerNewsList(page: Int?): Single<List<Entity.HackerNews>> {
        return hackerNewsDataSource.getHackerNewsList(page ?: 0)
    }

    override fun getHackerNewsListOB(page: Int?): Observable<List<Entity.HackerNews>> {
        return hackerNewsDataSource.getHackerNewsListOB(page ?: 0)
    }
}