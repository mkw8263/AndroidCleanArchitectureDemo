package com.example.mindevandroidcleanarchitecturedemo.vm

import androidx.lifecycle.MutableLiveData
import com.example.domain.Result
import com.example.domain.entity.Entity
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.base.MindevViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val hackerNewsUseCase: HackerNewsUseCase) : MindevViewModel() {

    val hackerNewsLiveData = MutableLiveData<Result<List<Entity.HackerNews>>>()
    val errorLiveData = MutableLiveData<Result<Throwable>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getList() {
        loadingLiveData.postValue(true)
        addDisposable(hackerNewsUseCase.execute(HackerNewsUseCase.Param(30))
            .subscribe { response, error ->
                loadingLiveData.postValue(false)
                if (error != null) errorLiveData.value = Result.error(error)
                else hackerNewsLiveData.value = Result.success(response)
            })
    }
}