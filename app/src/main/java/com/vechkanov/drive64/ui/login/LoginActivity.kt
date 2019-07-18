package com.vechkanov.drive64.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.vechkanov.drive64.ViewModelFactory
import com.vechkanov.drive64.ui.core.BaseActivity
import com.vk.sdk.VKSdk
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject
import com.vk.sdk.api.VKError
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import android.content.Intent
import android.widget.Toast
import com.vechkanov.drive64.R
import timber.log.Timber


class LoginActivity : BaseActivity<LoginViewModel>(), View.OnClickListener {

    private lateinit var vm: LoginViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun getViewModel(): LoginViewModel {
        vm = ViewModelProviders.of(this, factory)[LoginViewModel::class.java]
        return vm
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initLoginBtns()
        vm.vkEvent.observe(this, Observer { vkEventAction(it) })
    }

    private fun vkEventAction(event: VKEvent) {
        when (event) {
            VKEvent.SHOW_LOGIN_ERROR -> Timber.e("User not authorised in VK.")
            VKEvent.SHOW_LOGIN_SUCCESS -> {
                Timber.d("User successfully authorised in VK")
                Toast.makeText(this, "User successfully authorised in VK", Toast.LENGTH_SHORT).show()
            }
            VKEvent.SHOW_LOGIN -> VKSdk.login(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val vkOnActivityResult = VKSdk.onActivityResult(requestCode,
                resultCode,
                data,
                object: VKCallback<VKAccessToken> {
                    override fun onResult(res: VKAccessToken?) {
                        vm.onVKLoginSuccess()
                    }

                    override fun onError(error: VKError?) {
                        vm.onVKLoginError()
                        Timber.e("User not authorised in VK %s", error.toString())
                    }
                }
        )
        if (!vkOnActivityResult) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun initLoginBtns() {
        vk_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == vk_login) {
            vm.onVKLoginClick()
        }
    }


}
