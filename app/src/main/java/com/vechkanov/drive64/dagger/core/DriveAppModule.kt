package com.vechkanov.drive64.dagger.core

import android.content.Context
import com.google.gson.Gson
import com.vechkanov.drive64.DriveMainApplication
import com.vechkanov.drive64.ui.main.MainActivityViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DriveAppModule {
    @Singleton
    @Provides
    internal fun provideContext(application: DriveMainApplication): Context {
        return application
    }

    @Singleton
    @Provides
    internal fun providesGson(): Gson {
        return Gson()
    }

//    @Singleton
//    @Provides
//    internal fun provideUtils(context: Context): Utils {
//        return Utils(context)
//    }

}