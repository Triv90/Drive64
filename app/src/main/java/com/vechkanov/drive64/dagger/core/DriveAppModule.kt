package com.vechkanov.drive64.dagger.core

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.vechkanov.drive64.DB_NAME
import com.vechkanov.drive64.DriveMainApplication
import com.vechkanov.drive64.data.local.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DriveAppModule {
    @Singleton
    @Provides
    fun provideContext(application: DriveMainApplication): Context {
        return application
    }

    @Singleton
    @Provides
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Named("DB_NAME")
    fun provideDatabaseName(): String {
        return DB_NAME
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@Named("DB_NAME") dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()
    }

//    @Singleton
//    @Provides
//    internal fun provideUtils(context: Context): Utils {
//        return Utils(context)
//    }

}