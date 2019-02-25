package com.example.mindevandroidcleanarchitecturedemo.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class MindevViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}