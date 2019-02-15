package com.example.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.usecase.news.HackerNewsUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(val hackerNewsUseCase: HackerNewsUseCase) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    fun test() {
        Log.e("eee", "")
//        hackerNewsUseCase.getHackerNews(30)
//        hackerNewsUseCaseImpl.getHackerNews(30).subscribe { t1, _ ->
//            Log.e("", t1[0].title)
//        }.addTo(compositeDisposable)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}