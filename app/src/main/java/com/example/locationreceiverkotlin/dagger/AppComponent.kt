package com.example.locationreceiverkotlin.dagger

import com.example.locationreceiverkotlin.activity.LoginActivity
import com.example.locationreceiverkotlin.activity.MenuActivity
import com.example.locationreceiverkotlin.activity.SplashScreen
import com.example.locationreceiverkotlin.dagger.module.FirebaseModule
import com.example.locationreceiverkotlin.dagger.module.RetrofitModule
import com.example.locationreceiverkotlin.dagger.module.RoomModule
import com.example.locationreceiverkotlin.mvp.model.DatabaseModelImpl
import com.example.locationreceiverkotlin.mvp.model.RetrofitModel
import com.example.locationreceiverkotlin.mvp.presenter.MapsPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        FirebaseModule::class,
        RetrofitModule::class,
        RoomModule::class]
)
@Singleton
interface AppComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(menuActivity: MenuActivity)
    fun inject(splashScreen: SplashScreen)
    fun inject(mapsPresenter: MapsPresenter)
    fun inject(databaseModelImpl: DatabaseModelImpl)
    fun inject(retrofitModel: RetrofitModel)
}