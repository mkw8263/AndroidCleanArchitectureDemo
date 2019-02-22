package com.example.mindevandroidcleanarchitecturedemo.vm

import androidx.lifecycle.MutableLiveData
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.base.MindevViewModel
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity
import com.example.mindevandroidcleanarchitecturedemo.mapper.PresentationHackerNewsMapper
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val hackerNewsUseCase: HackerNewsUseCase,
    private val presentationHackerNewsMapper: PresentationHackerNewsMapper
) : MindevViewModel() {

    sealed class Result {
        data class NewsData(val data: List<PresentationEntity.HackerNews>) : Result()
        data class ShowError(val throwable: Throwable) : Result()
        data class ProgressBarVisibility(val isLoading: Boolean) : Result()
    }

    val liveResult = MutableLiveData<Result>()

    fun getList() {
        liveResult.postValue(Result.ProgressBarVisibility(true))
        addDisposable(hackerNewsUseCase.execute(HackerNewsUseCase.Param(30))
            .subscribe { response, error ->
                liveResult.postValue(Result.ProgressBarVisibility(false))
                if (error != null) liveResult.value = Result.ShowError(error)
                else liveResult.value =
                    Result.NewsData(response.map { presentationHackerNewsMapper.mapToView(it) })
            })
    }
}