package com.example.mindevandroidcleanarchitecturedemo.di.module

import com.example.mindevandroidcleanarchitecturedemo.ui.MainActivity
import com.example.mindevandroidcleanarchitecturedemo.di.qualifier.PerActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity
}