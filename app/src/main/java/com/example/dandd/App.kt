package com.example.dandd

import android.app.Application
import com.example.dandd.di.daoModule
import com.example.dandd.di.retrofitModule
import com.example.dandd.di.rootModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(rootModule, retrofitModule, daoModule)
        }
    }
}