package com.example.data.repository

import com.example.data.mapper.DataHackerNewsMapper
import com.example.data.source.news.local.HackerNewsLocalDataSource
import com.example.data.source.news.remote.HackerNewsRemoteDataSource
import com.example.domain.HackerNewsRepository
import com.example.domain.entities.DomainEntity
import io.reactivex.Observable
import io.reactivex.Single

class HackerNewsRepositoryImpl(
    private val hackerNewsLocalDataSource: HackerNewsLocalDataSource,
    private val hackerNewsRemoteDataSource: HackerNewsRemoteDataSource,
    private val hackerNewsMapper: DataHackerNewsMapper
) : HackerNewsRepository {
    override fun getHackerNewsList(page: Int?): Single<List<DomainEntity.HackerNews>> {
        return hackerNewsRemoteDataSource.getHackerNewsList(page ?: 0).map { data ->
            hackerNewsMapper.mapFromEntity(data)
        }
    }

    override fun getHackerNewsListOB(page: Int?): Observable<List<DomainEntity.HackerNews>> {
        return hackerNewsRemoteDataSource.getHackerNewsListOB(page ?: 0).map { data ->
            hackerNewsMapper.mapFromEntity(data)
        }
    }
}
