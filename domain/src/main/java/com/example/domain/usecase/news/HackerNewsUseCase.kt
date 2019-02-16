package com.example.domain.usecase.news

import com.example.domain.entity.Entity
import com.example.domain.usecase.BaseUseCase
import io.reactivex.Single

interface HackerNewsUseCase : BaseUseCase {
    fun getHackerNews(page: Int): Single<List<Entity.HackerNews>>
}