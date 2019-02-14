package com.example.data.source.local

import com.example.data.source.HackerNewsDataSource
import com.example.domain.entity.Entity
import io.reactivex.Single

class HackerNewsLocalDataSource : HackerNewsDataSource {
    override fun getHackerNewsList(page: Int): Single<List<Entity.HackerNews>> {
        return Single.never()
    }
}