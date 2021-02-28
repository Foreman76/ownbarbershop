package ru.int24.ownbarbershop.di

import android.app.Application

class App:Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger(){
        appComponent = DaggerAppComponent.builder()
                .build()
    }
}