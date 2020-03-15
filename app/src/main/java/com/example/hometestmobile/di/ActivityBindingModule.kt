package com.example.hometestmobile.di

import com.example.hometestmobile.MainActivity
import com.example.hometestmobile.fragments.CitiesFragment
import com.example.hometestmobile.fragments.DistrictFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindCitiesFragment(): CitiesFragment

    @ContributesAndroidInjector
    abstract fun bindDistrictFragment(): DistrictFragment
}