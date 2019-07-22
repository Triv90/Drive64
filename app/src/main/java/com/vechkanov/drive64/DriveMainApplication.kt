package com.vechkanov.drive64

import com.vechkanov.drive64.dagger.core.DaggerDriveMainComponent
import com.vk.sdk.VKSdk
import com.yandex.mapkit.MapKitFactory
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class DriveMainApplication : DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
        VKSdk.initialize(applicationContext)
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        if (BuildConfig.DEBUG) {
            Timber.plant()
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerDriveMainComponent.builder().create(this)
    }
}
