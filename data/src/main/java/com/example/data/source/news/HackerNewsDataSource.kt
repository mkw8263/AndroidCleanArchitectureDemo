package com.example.data.source.news

import com.example.data.source.DataSource
import com.example.domain.entity.Entity
import io.reactivex.Single

interface HackerNewsDataSource : DataSource {
    fun getHackerNewsList(page: Int): Single<List<Entity.HackerNews>>
}