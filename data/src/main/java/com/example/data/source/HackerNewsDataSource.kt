package com.example.data.source

import com.example.domain.entity.Entity
import io.reactivex.Single

interface HackerNewsDataSource : DataSource {
    fun getHackerNewsList(page: Int): Single<List<Entity.HackerNews>>
}