package com.example.mindevandroidcleanarchitecturedemo.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.Entity
import com.example.mindevandroidcleanarchitecturedemo.R
import com.example.mindevandroidcleanarchitecturedemo.base.MindevActivity
import com.example.mindevandroidcleanarchitecturedemo.vm.MainViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : MindevActivity<MainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    override val viewModel: MainViewModel
        get() = ViewModelProviders.of(this, factory)[MainViewModel::class.java]

    override val layoutResource: Int
        get() = R.layout.activity_main

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.run()
            .subscribe { result ->
                when (result) {
                    is MainViewModel.Result.HackerNewsList -> setUpRecycler(result.items)
                    is MainViewModel.Result.ProgressBarStatus -> setUpProgressBar(result.isLoading)
                }
            }.addTo(compositeDisposable)
    }

    private fun setUpProgressBar(loading: Boolean) {
        progress.visibility = if (loading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun setUpRecycler(items: List<Entity.HackerNews>) {
        recyclerList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(items)
        }
    }
}
