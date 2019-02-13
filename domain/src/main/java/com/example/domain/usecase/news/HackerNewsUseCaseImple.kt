package com.example.domain.usecase.news

import com.example.domain.entity.Entity
import io.reactivex.Single

class HackerNewsUseCaseImple:HackerNewsUseCase {
    override fun getHackerNews(): Single<List<Entity.HackerNews>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}