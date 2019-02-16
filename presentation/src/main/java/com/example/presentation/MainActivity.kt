package com.example.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.Result
import com.example.domain.entity.Entity
import com.example.presentation.base.MindevActivity
import com.example.presentation.extension.observe
import com.example.presentation.vm.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : MindevActivity<MainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
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
        observe(viewModel.hackerNewsLiveData, ::initializeUI)
        observe(viewModel.errorLiveData, ::errorUI)
        observe(viewModel.loadingLiveData, ::loadUI)
    }

    private fun errorUI(throwable: Result<Throwable>) {

    }

    private fun loadUI(isLoading: Boolean) {

    }

    private fun initializeUI(response: Result<List<Entity.HackerNews>>) {
        setUpRecycler(response.getData().orEmpty())
    }

    private fun setUpRecycler(items: List<Entity.HackerNews>) {
        recyclerList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(items)
        }
    }
}
