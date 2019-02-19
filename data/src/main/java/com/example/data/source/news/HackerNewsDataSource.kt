package com.example.data.source.news

import com.example.data.entities.DataEntity
import com.example.data.source.DataSource
import io.reactivex.Observable
import io.reactivex.Single

interface HackerNewsDataSource : DataSource {
    fun getHackerNewsList(page: Int): Single<List<DataEntity.HackerNews>>
    fun getHackerNewsListOB(page: Int): Observable<List<DataEntity.HackerNews>>
}