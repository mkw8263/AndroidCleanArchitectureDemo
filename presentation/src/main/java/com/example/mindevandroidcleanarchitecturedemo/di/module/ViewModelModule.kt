package com.example.mindevandroidcleanarchitecturedemo.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mindevandroidcleanarchitecturedemo.di.ViewModelFactory
import com.example.mindevandroidcleanarchitecturedemo.di.qualifier.ViewModelKey
import com.example.mindevandroidcleanarchitecturedemo.vm.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}