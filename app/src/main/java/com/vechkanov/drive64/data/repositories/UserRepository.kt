package com.vechkanov.drive64.data.repositories

import com.vechkanov.drive64.data.model.User
import dagger.Reusable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

@Reusable
class UserRepository @Inject
constructor() {
    // Return user Id or nothing
    fun saveUser(user: User, saveAsCurrent: Boolean): Maybe<Int> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getUser(userId: Long): Maybe<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun currentUser(userId: Long): Maybe<User> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}