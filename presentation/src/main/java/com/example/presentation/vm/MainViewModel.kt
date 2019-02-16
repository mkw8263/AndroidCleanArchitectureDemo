package com.example.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.news.HackerNewsUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainViewModel @Inject constructor(private val hackerNewsUseCase: HackerNewsUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun test() {
        hackerNewsUseCase.getHackerNews(30)
            .subscribe { response, _ ->
            }.addTo(compositeDisposable)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}