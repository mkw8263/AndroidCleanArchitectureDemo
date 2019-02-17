package com.example.mindevandroidcleanarchitecturedemo.di.module

import android.content.Context
import com.example.mindevandroidcleanarchitecturedemo.MindevApplication
import com.example.mindevandroidcleanarchitecturedemo.di.qualifier.ApplicationContext
import dagger.Binds
import dagger.Module

@Module(includes = [NetWorkModule::class])
abstract class AppModule {

    @ApplicationContext
    @Binds
    abstract fun provideApplicationContext(mindevApplication: MindevApplication): Context
}