package com.example.domain

import com.example.domain.entities.DomainEntity
import io.reactivex.Observable
import io.reactivex.Single

interface HackerNewsRepository : Repository {

    fun getHackerNewsList(page: Int?): Single<List<DomainEntity.HackerNews>>

    fun getHackerNewsListOB(page: Int?): Observable<List<DomainEntity.HackerNews>>
}