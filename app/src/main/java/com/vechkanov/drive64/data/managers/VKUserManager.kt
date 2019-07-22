package com.vechkanov.drive64.data.managers

import android.util.Log
import com.vechkanov.drive64.data.dtoModels.UserVK
import com.vechkanov.drive64.data.repositories.UserRepository
import com.vechkanov.drive64.data.repositories.VKNetworkRepository
import dagger.Reusable
import io.reactivex.Maybe
import javax.inject.Inject

@Reusable
class VKUserManager @Inject
constructor(private val userRepository: UserRepository,
            private val vkNetworkRepository: VKNetworkRepository) {
    fun saveVkUserAsCurrent(userVk: UserVK): Maybe<Long> {
        return userRepository.saveUser(userVk.parse(), true)
    }

    fun retrieveCurrentUser(): Maybe<UserVK> {
        return vkNetworkRepository.retrieveCurrentUser()
    }
}