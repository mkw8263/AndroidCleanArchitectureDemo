package com.example.data.repository

import com.example.data.source.news.HackerNewsDataSource
import com.example.domain.HackerNewsRepository
import com.example.domain.entity.Entity
import io.reactivex.Single

class HackerNewsRepositoryImpl(val hackerNewsDataSource: HackerNewsDataSource) :
    HackerNewsRepository {
    override fun getHackerNewsList(): Single<List<Entity.HackerNews>> {
        return hackerNewsDataSource.getHackerNewsList(30)
    }
}