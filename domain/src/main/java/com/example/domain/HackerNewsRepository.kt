package com.example.domain

import com.example.domain.entity.Entity
import io.reactivex.Observable
import io.reactivex.Single

interface HackerNewsRepository : Repository {

    fun getHackerNewsList(page: Int?): Single<List<Entity.HackerNews>>

    fun getHackerNewsListOB(page: Int?): Observable<List<Entity.HackerNews>>
}