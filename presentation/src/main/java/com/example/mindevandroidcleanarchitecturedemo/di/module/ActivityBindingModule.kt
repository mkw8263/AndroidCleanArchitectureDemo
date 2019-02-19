package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.mindevandroidcleanarchitecturedemo.di.module.main.MainModule
import com.example.mindevandroidcleanarchitecturedemo.di.qualifier.PerActivity
import com.example.mindevandroidcleanarchitecturedemo.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindingMainActivity(): MainActivity
}