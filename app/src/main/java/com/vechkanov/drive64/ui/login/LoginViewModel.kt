package com.vechkanov.drive64.ui.login

import android.util.Log
import com.vechkanov.drive64.data.dtoModels.UserVK
import com.vechkanov.drive64.data.managers.VKUserManager
import com.vechkanov.drive64.ui.core.BaseViewModel
import com.vechkanov.drive64.utils.SingleLiveEvent
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import org.jetbrains.annotations.Async
import javax.inject.Inject

class LoginViewModel
@Inject
constructor(private val vkUserManager: VKUserManager) : BaseViewModel() {
    val vkEvent = SingleLiveEvent<VKEvent>()

    fun onVKLoginClick() {
        vkEvent.value = VKEvent.SHOW_LOGIN
    }

    fun onVKLoginSuccess() {
        vkUserManager.retrieveCurrentUser()
                .map {
                    vkUserManager.saveVkUserAsCurrent(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                        onSuccess = {
                            //vkEvent.value = VKEvent.SHOW_LOGIN_SUCCESS
                            vkEvent.value = VKEvent.OPEN_MAIN_ACTIVITY
                        },
                        onError = {
                            onVKLoginError()
                        },
                        onComplete = {
                            onVKLoginError()
                        }
                )
    }

    fun onVKLoginError() {
        vkEvent.value = VKEvent.SHOW_LOGIN_ERROR
    }
}
