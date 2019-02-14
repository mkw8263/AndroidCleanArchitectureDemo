package com.example.mindevandroidcleanarchitecturedemo

import android.app.Activity
import android.app.Application
import com.example.mindevandroidcleanarchitecturedemo.di.DaggerMindevComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MindevApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        print("")
        DaggerMindevComponent.builder()
            .application(this)
            .build()
            .inject(this)

    }
}