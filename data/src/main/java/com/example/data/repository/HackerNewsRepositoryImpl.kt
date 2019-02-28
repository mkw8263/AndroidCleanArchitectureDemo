package com.example.data.repository

import com.example.data.mapper.DataHackerNewsMapper
import com.example.data.source.news.local.HackerNewsLocalData
import com.example.data.source.news.remote.HackerNewsRemoteData
import com.example.domain.HackerNewsRepository
import com.example.domain.entities.DomainEntity
import io.reactivex.Observable
import io.reactivex.Single

open class HackerNewsRepositoryImpl(
    private val hackerNewsLocalData: HackerNewsLocalData,
    private val hackerNewsRemoteData: HackerNewsRemoteData,
    private val hackerNewsMapper: DataHackerNewsMapper
) : HackerNewsRepository {
    override fun getHackerNewsList(page: Int?): Single<List<DomainEntity.HackerNews>> {
        return hackerNewsRemoteData.getHackerNewsList(page ?: 0).map { data ->
            hackerNewsMapper.mapFromEntity(data)
        }
    }

    override fun getHackerNewsListOB(page: Int?): Observable<List<DomainEntity.HackerNews>> {
        return hackerNewsRemoteData.getHackerNewsListOB(page ?: 0).map { data ->
            hackerNewsMapper.mapFromEntity(data)
        }
    }
}
