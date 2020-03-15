package com.example.hometestmobile.di

import com.example.hometestmobile.HomeTestApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBindingModule::class
    ]
)
interface AppComponent : AndroidInjector<HomeTestApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun init(app: HomeTestApp): Builder

        fun build(): AppComponent
    }
}