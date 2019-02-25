package com.example.mindevandroidcleanarchitecturedemo.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.base.MindevViewModel
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity
import com.example.mindevandroidcleanarchitecturedemo.mapper.PresentationHackerNewsMapper
import io.reactivex.rxkotlin.addTo

class MainViewModel(
    private val hackerNewsUseCase: HackerNewsUseCase,
    private val presentationHackerNewsMapper: PresentationHackerNewsMapper
) : MindevViewModel() {

    sealed class Result {
        data class NewsData(val data: List<PresentationEntity.HackerNews>) : Result()
        data class ShowError(val throwable: Throwable) : Result()
        data class ProgressBarVisibility(val isLoading: Boolean) : Result()
    }

    val mutableLiveResult = MutableLiveData<Result>()
    val liveResult: LiveData<Result> = mutableLiveResult

    fun getList() {
        mutableLiveResult.value = Result.ProgressBarVisibility(true)
        hackerNewsUseCase.execute(HackerNewsUseCase.Param(30))
            .subscribe { response, error ->
                mutableLiveResult.value = Result.ProgressBarVisibility(false)
                if (error != null) mutableLiveResult.value = Result.ShowError(error)
                else mutableLiveResult.value =
                    Result.NewsData(response.map { presentationHackerNewsMapper.mapToView(it) })
            }.addTo(compositeDisposable)
    }
}