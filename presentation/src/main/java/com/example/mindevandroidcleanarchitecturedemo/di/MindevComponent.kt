package com.example.mindevandroidcleanarchitecturedemo.di

import com.example.mindevandroidcleanarchitecturedemo.MindevApplication
import com.example.mindevandroidcleanarchitecturedemo.di.module.ActivityBindingModule
import com.example.mindevandroidcleanarchitecturedemo.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class]
)
interface MindevComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MindevApplication): Builder

        fun build(): MindevComponent
    }

    fun inject(app: MindevApplication)
}
