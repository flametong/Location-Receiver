package com.example.locationreceiverkotlin.dagger.module

import androidx.room.Room
import com.example.locationreceiverkotlin.App
import com.example.locationreceiverkotlin.room.AppDatabase
import com.example.locationreceiverkotlin.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            App.context,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .build()
    }
}