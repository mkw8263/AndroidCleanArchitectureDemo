package com.example.domain.usecase.news

import com.example.domain.HackerNewsRepository
import com.example.domain.Result
import com.example.domain.entity.Entity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HackerNewsUseCaseImpl(private val hackerNewsRepository: HackerNewsRepository) : HackerNewsUseCase {
    override fun getHackerNews(page: Int): Single<Result<List<Entity.HackerNews>>> {
        return hackerNewsRepository.getHackerNewsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}