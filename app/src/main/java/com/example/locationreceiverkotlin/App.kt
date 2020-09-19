package com.example.locationreceiverkotlin

import android.app.Application
import com.example.locationreceiverkotlin.dagger.AppComponent
import com.example.locationreceiverkotlin.dagger.DaggerAppComponent

class App : Application() {
    companion object {
        lateinit var context: App
            private set
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        appComponent = DaggerAppComponent.create()
    }
}