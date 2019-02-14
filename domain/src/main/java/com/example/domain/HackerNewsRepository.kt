package com.example.domain

import com.example.domain.entity.Entity
import io.reactivex.Single

interface HackerNewsRepository : Repository {

    fun getHackerNewsList(): Single<List<Entity.HackerNews>>
}