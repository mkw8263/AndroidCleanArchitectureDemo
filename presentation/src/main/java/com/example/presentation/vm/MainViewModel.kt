package com.example.presentation.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel() {

    fun test() {
       Log.e("!","!")
    }
    override fun onCleared() {
        super.onCleared()
    }
}