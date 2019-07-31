package com.vechkanov.drive64.ui.main

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vechkanov.drive64.R
import com.vechkanov.drive64.ViewModelFactory
import com.vechkanov.drive64.ui.core.BaseActivity
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

const val LOCATION_REQUEST_CODE = 1

class MainActivity: BaseActivity<MainActivityViewModel>() {


    private lateinit var vm: MainActivityViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getViewModel(): MainActivityViewModel {
        vm = ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]
        return vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)


        requestPermissionsSafely(arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION), LOCATION_REQUEST_CODE)
        vm.moveCameraEvent.observe(this, Observer { moveCamera(it) })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> {
                if (grantResults.size == 2 && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    // Permissions granted
                    vm.getMapPermissions()
                } else {
                    // No permissions
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun moveCamera(position: CameraPosition) {
        map_view.map.move(
                position,
                Animation(Animation.Type.SMOOTH, 5f),
                null)
    }

    override fun onStop() {
        map_view.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        map_view.onStart()
    }
}