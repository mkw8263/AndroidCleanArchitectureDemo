package com.example.mindevandroidcleanarchitecturedemo

import android.app.Application
import com.example.mindevandroidcleanarchitecturedemo.di.DaggerMindevComponent

class MindevApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        DaggerMindevComponent.builder()
            .application(this)
            .build()

    }
}