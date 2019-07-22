package com.vechkanov.drive64.data.repositories

import com.vechkanov.drive64.data.model.User
import com.vechkanov.drive64.data.local.room.AppDatabase
import com.vechkanov.drive64.data.local.sharedPref.CURRENT_USER_PREF_KEY
import com.vechkanov.drive64.data.local.sharedPref.SharedPreferenceProvider
import dagger.Reusable
import io.reactivex.Maybe
import javax.inject.Inject

@Reusable
class UserRepository @Inject
constructor(
        private val appDatabase: AppDatabase,
        private val sharedPrefProvider: SharedPreferenceProvider) {
    // Return user Id or nothing
    fun saveUser(user: User, saveAsCurrent: Boolean): Maybe<Long> {
        val insertedId = appDatabase.userDao().insert(user)
        if (saveAsCurrent) {
            sharedPrefProvider.saveToSharedPref(CURRENT_USER_PREF_KEY, insertedId)
        }
        return Maybe.just(insertedId)
    }

    fun getUser(userId: Long): Maybe<User> {
        return Maybe.just(appDatabase.userDao().get(userId))
    }

    fun currentUser(): Maybe<User> {
        val currentUserId = sharedPrefProvider.getSharedPref(CURRENT_USER_PREF_KEY, 0)
        if (currentUserId != 0L) {
            return Maybe.just(appDatabase.userDao().get(currentUserId))
        }
        return Maybe.empty()
    }
}