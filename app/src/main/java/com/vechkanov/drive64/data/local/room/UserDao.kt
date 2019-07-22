package com.vechkanov.drive64.data.local.room

import androidx.room.*
import com.vechkanov.drive64.data.model.User
import java.util.*

@Dao
interface UserDao {
    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): User

    @Query("SELECT * FROM users WHERE id == :id")
    fun get(id: Long): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>): List<Long>

    @Query("SELECT * FROM users")
    fun loadAll(): List<User>

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: List<Int>): List<User>
}