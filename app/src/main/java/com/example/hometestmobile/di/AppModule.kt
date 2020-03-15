package com.example.hometestmobile.di

import com.example.hometestmobile.network.SapoService
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun providesSapoService() = SapoService.create()
}