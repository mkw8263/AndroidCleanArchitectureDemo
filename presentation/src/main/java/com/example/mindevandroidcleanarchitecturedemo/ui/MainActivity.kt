package com.example.mindevandroidcleanarchitecturedemo.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mindevandroidcleanarchitecturedemo.R
import com.example.mindevandroidcleanarchitecturedemo.base.MindevActivity
import com.example.mindevandroidcleanarchitecturedemo.di.module.MainViewModelFactory
import com.example.mindevandroidcleanarchitecturedemo.entities.PresentationEntity
import com.example.mindevandroidcleanarchitecturedemo.extension.observe
import com.example.mindevandroidcleanarchitecturedemo.extension.showToast
import com.example.mindevandroidcleanarchitecturedemo.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : MindevActivity<MainViewModel>() {

    @Inject
    lateinit var factory: MainViewModelFactory
    override val viewModel: MainViewModel
        get() = ViewModelProviders.of(this, factory)[MainViewModel::class.java]

    override val layoutResource: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setObserver()
        viewModel.getList()
    }

    private fun setObserver() {
        observe(viewModel.liveResult, ::liveDataResult)
    }

    private fun liveDataResult(result: MainViewModel.Result) {
        when (result) {
            is MainViewModel.Result.NewsData -> setUpRecycler(result.data)
            is MainViewModel.Result.ShowError -> showToast(result.throwable.message.orEmpty())
            is MainViewModel.Result.ProgressBarVisibility -> progressBarVisibility(result.isLoading)
        }
    }

    private fun progressBarVisibility(isLoading: Boolean) {
        progress.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setUpRecycler(items: List<PresentationEntity.HackerNews>) {
        recyclerList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(items)
        }
    }
}
