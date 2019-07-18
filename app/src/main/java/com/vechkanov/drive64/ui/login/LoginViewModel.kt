package com.vechkanov.drive64.ui.login

import com.google.gson.Gson
import com.vechkanov.drive64.data.dtoModels.UserVK
import com.vechkanov.drive64.data.managers.VKUserManager
import com.vechkanov.drive64.ui.core.BaseViewModel
import com.vechkanov.drive64.utils.SingleLiveEvent
import com.vk.sdk.api.VKApi
import com.vk.sdk.api.VKError
import com.vk.sdk.api.VKRequest.VKRequestListener
import com.vk.sdk.api.VKResponse
import io.reactivex.rxkotlin.subscribeBy
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
                .map { vkUserManager.saveVkUserAsCurrent(it) }
                .subscribeBy (
                onSuccess = {
                    vkEvent.value = VKEvent.SHOW_LOGIN_SUCCESS
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
