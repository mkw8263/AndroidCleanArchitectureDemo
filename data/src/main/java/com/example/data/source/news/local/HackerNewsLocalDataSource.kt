package com.example.data.source.news.local

import com.example.data.entities.DataEntity
import com.example.data.source.news.HackerNewsDataSource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class HackerNewsLocalDataSource @Inject constructor() : HackerNewsDataSource {
    override fun getHackerNewsList(page: Int): Single<List<DataEntity.HackerNews>> {
        return Single.never()
    }

    override fun getHackerNewsListOB(page: Int): Observable<List<DataEntity.HackerNews>> {
        return Observable.empty()
    }
}