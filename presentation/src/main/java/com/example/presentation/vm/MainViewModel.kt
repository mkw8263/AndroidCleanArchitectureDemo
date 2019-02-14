package com.example.presentation.vm

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    fun test() {
        print("ㅋㅋㅋㅋ")
    }
    override fun onCleared() {
        super.onCleared()
    }
}