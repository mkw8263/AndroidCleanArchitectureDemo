package com.example.data.mapper

import com.example.data.entities.DataEntity
import com.example.domain.entities.DomainEntity
import javax.inject.Inject

open class DataHackerNewsMapper @Inject constructor() :
    DataMapper<List<DataEntity.HackerNews>, List<DomainEntity.HackerNews>> {
    override fun mapFromEntity(type: List<DataEntity.HackerNews>): List<DomainEntity.HackerNews> {
        return type.map { data ->
            DomainEntity.HackerNews(data.comments_count, data.id, data.time_ago, data.title)
        }
    }

    override fun mapToEntity(type: List<DomainEntity.HackerNews>): List<DataEntity.HackerNews> {
        return type.map { data ->
            DataEntity.HackerNews().apply {
                comments_count = data.comments_count
                time_ago = data.time_ago
                title = data.title
                id = data.id
            }
        }
    }
}