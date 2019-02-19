package com.example.mindevandroidcleanarchitecturedemo.mapper

import com.example.domain.entities.DomainEntity
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity
import javax.inject.Inject

class PresentationHackerNewsMapper @Inject constructor() :
    PresenterMapper<DomainEntity.HackerNews, PresentationEntity.HackerNews> {
    override fun mapToView(type: DomainEntity.HackerNews): PresentationEntity.HackerNews {
        return PresentationEntity.HackerNews(type.comments_count, type.id, type.time_ago, type.title)
    }
}