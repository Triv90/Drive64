package com.vechkanov.drive64.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var name: String,
        var familyName: String,
        var nickname: String = "",
        var genderMale: Boolean? = null,
        var photoUrl: String? = null,
        var rating: Int = 0,
        var phone: String? = null,
        var vkId: Long? = null,
        var facebookId: String? = null,
        var googleId: String? = null
       // var hiddenFields: List<String> = emptyList()
)