package com.example.hometestmobile

import com.example.hometestmobile.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class HomeTestApp : DaggerApplication() {override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder()
            .init(this)
            .build()
}