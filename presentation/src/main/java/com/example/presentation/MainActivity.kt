package com.example.presentation

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.presentation.base.MindevActivity
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
        setContentView(R.layout.activity_main)
        viewModel.test()

//        setUpRecycler()
    }

//    private fun setUpRecycler() {
//        recycler.apply {
//            layoutManager = LinearLayoutManager(this@MainActivity)
//        }
//    }
}
