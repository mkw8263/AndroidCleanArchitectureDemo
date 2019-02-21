package com.example.data.source.news.local

import com.example.data.entities.DataEntity
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

open class HackerNewsLocalDataSource @Inject constructor() {
    fun getHackerNewsList(page: Int): Single<List<DataEntity.HackerNews>> {
        return Single.never()
    }

    fun getHackerNewsListOB(page: Int): Observable<List<DataEntity.HackerNews>> {
        return Observable.empty()
    }
}