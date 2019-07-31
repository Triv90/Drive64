package com.vechkanov.drive64.ui.main

import com.vechkanov.drive64.services.LocationProvider
import com.vechkanov.drive64.ui.core.BaseViewModel
import com.vechkanov.drive64.utils.SingleLiveEvent
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import javax.inject.Inject

class MainActivityViewModel
@Inject
constructor(
        private val locationProvider: LocationProvider
) : BaseViewModel() {
    val moveCameraEvent = SingleLiveEvent<CameraPosition>()

    fun getMapPermissions() {
        moveCameraEvent.value = CameraPosition(
                Point(locationProvider.location?.latitude!!, locationProvider.location?.longitude!!),
                14f,
                0f,
                0f
        )
    }
}