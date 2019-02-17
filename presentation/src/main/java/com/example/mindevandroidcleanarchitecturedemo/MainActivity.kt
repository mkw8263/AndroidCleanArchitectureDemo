package com.example.mindevandroidcleanarchitecturedemo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.Result
import com.example.domain.entity.Entity
import com.example.mindevandroidcleanarchitecturedemo.base.MindevActivity
import com.example.mindevandroidcleanarchitecturedemo.extension.observe
import com.example.mindevandroidcleanarchitecturedemo.extension.showToast
import com.example.mindevandroidcleanarchitecturedemo.vm.MainViewModel
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

    private fun errorUI(state: Result<Throwable>) {
        showToast(state.throwable?.message.orEmpty())
    }

    private fun loadUI(isLoading: Boolean) {
        progress.visibility = if (isLoading) View.VISIBLE else View.GONE
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
