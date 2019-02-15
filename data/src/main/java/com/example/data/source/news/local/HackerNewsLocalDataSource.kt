package com.example.data.source.news.local

import com.example.data.source.news.HackerNewsDataSource
import com.example.domain.entity.Entity
import io.reactivex.Single

class HackerNewsLocalDataSource : HackerNewsDataSource {
    override fun getHackerNewsList(page: Int): Single<List<Entity.HackerNews>> {
        return Single.never()
    }
}