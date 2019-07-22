package com.vechkanov.drive64.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vechkanov.drive64.data.model.User

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
