package com.vechkanov.drive64.data.dtoModels

import com.google.gson.annotations.SerializedName
import com.vechkanov.drive64.data.model.User

data class UserVK(
        val id: Long,
        @SerializedName("first_name")
        val name: String,
        @SerializedName("last_name")
        val familyName: String
) {
    fun parse(): User {
        return User(
                name = name,
                familyName = familyName,
                vkId = id
        )
    }
}
