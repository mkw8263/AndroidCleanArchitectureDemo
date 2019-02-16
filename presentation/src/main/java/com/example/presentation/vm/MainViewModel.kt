package com.example.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.Result
import com.example.domain.entity.Entity
import com.example.domain.usecase.news.HackerNewsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(private val hackerNewsUseCase: HackerNewsUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val hackerNewsLiveData = MutableLiveData<Result<List<Entity.HackerNews>>>()
    val errorLiveData = MutableLiveData<Result<Throwable>>()
    val loadingLiveData = MutableLiveData<Boolean>()

    fun getList() {
        loadingLiveData.postValue(true)
        hackerNewsUseCase.getHackerNews(30)
            .subscribe { response, error ->
                loadingLiveData.postValue(false)
                if (error != null) errorLiveData.value = Result.error(error)
                else hackerNewsLiveData.value = Result.success(response)
            }.addTo(compositeDisposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}