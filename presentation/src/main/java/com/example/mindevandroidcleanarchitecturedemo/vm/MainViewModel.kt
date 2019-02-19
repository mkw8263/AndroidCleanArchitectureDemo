package com.example.mindevandroidcleanarchitecturedemo.vm

import androidx.lifecycle.MutableLiveData
import com.example.domain.Result
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.base.MindevViewModel
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity
import com.example.mindevandroidcleanarchitecturedemo.mapper.PresentationHackerNewsMapper
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val hackerNewsUseCase: HackerNewsUseCase,
    private val presentationHackerNewsMapper: PresentationHackerNewsMapper
) : MindevViewModel() {

    val hackerNewsLiveData = MutableLiveData<Result<List<PresentationEntity.HackerNews>>>()
    val errorLiveData = MutableLiveData<Result<Throwable>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getList() {
        loadingLiveData.postValue(true)
        addDisposable(hackerNewsUseCase.execute(HackerNewsUseCase.Param(30))
            .subscribe { response, error ->
                loadingLiveData.postValue(false)
                if (error != null) errorLiveData.value = Result.error(error)
                else hackerNewsLiveData.value =
                    Result.success(response.map { presentationHackerNewsMapper.mapToView(it) })
            })
    }
}