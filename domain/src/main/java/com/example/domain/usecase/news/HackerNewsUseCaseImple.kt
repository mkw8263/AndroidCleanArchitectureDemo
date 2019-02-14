package com.example.domain.usecase.news

import com.example.domain.HackerNewsRepository
import com.example.domain.entity.Entity
import io.reactivex.Single

class HackerNewsUseCaseImple(private val hackerNewsRepository: HackerNewsRepository) : HackerNewsUseCase {
    override fun getHackerNews(page: Int): Single<List<Entity.HackerNews>> {
        return hackerNewsRepository.getHackerNewsList()
    }
}