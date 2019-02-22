package com.example.domain.usecase.news

import com.example.domain.HackerNewsRepository
import com.example.domain.entities.DomainEntity
import com.example.domain.extension.networkCommunityThread
import com.example.domain.usecase.SingleUseCase
import io.reactivex.Single

open class HackerNewsUseCase(private val hackerNewsRepository: HackerNewsRepository) :
    SingleUseCase<HackerNewsUseCase.Param, List<DomainEntity.HackerNews>> {

    override fun execute(params: Param?): Single<List<DomainEntity.HackerNews>> {
        return hackerNewsRepository.getHackerNewsList(params?.page)
            .networkCommunityThread()
    }
    data class Param(val page: Int)
}

//class HackerNewsUseCase2(private val hackerNewsRepository: HackerNewsRepository) :
//    ObservableUseCase<HackerNewsUseCase2.Param, List<DomainEntity.HackerNews>> {
//
//    override fun execute(params: Param?): Observable<List<DomainEntity.HackerNews>> {
//        return hackerNewsRepository.getHackerNewsListOB(params?.page)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }
//    data class Param(val page: Int)
//}