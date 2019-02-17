package com.example.data.source.news.local

import com.example.data.source.news.HackerNewsDataSource
import com.example.domain.entity.Entity
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class HackerNewsLocalDataSource @Inject constructor() : HackerNewsDataSource {
    override fun getHackerNewsList(page: Int): Single<List<Entity.HackerNews>> {
        return Single.never()
    }

    override fun getHackerNewsListOB(page: Int): Observable<List<Entity.HackerNews>> {
        return Observable.empty()
    }
}