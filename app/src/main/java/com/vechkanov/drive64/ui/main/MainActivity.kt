package com.vechkanov.drive64.ui.main

import android.os.Bundle
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

class MainActivity: BaseActivity<MainActivityViewModel>() {
    private var mMainViewModel: MainActivityViewModel? = null

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getViewModel(): MainActivityViewModel {
        mMainViewModel = ViewModelProviders.of(this, factory)[MainActivityViewModel::class.java]
        return mMainViewModel!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        // Перемещение камеры в центр Санкт-Петербурга.
        map_view.map.move(
                CameraPosition(Point(59.945933, 30.320045), 14.0f, 0.0f, 0.0f),
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