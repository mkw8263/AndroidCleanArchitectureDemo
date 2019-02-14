package com.example.presentation.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity

abstract class MindevActivity<T : ViewModel> : DaggerAppCompatActivity() {
    abstract val layoutResource: Int
    abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
    }
}