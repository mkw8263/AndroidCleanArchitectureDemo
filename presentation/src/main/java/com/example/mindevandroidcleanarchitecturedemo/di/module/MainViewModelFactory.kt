package com.example.mindevandroidcleanarchitecturedemo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.news.HackerNewsUseCase
import com.example.mindevandroidcleanarchitecturedemo.mapper.PresentationHackerNewsMapper
import com.example.mindevandroidcleanarchitecturedemo.vm.MainViewModel

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val hackerNewsUseCase: HackerNewsUseCase,
    private val presentationHackerNewsMapper: PresentationHackerNewsMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(hackerNewsUseCase, presentationHackerNewsMapper) as T
    }
}