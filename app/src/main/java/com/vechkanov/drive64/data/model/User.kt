package com.vechkanov.drive64.data.model

data class User(
        var id: Int = -1,
        var name: String,
        var familyName: String,
        var nickname: String = "",
        var genderMale: Boolean? = null,
        var photoUrl: String? = null,
        var rating: Int = 0,
        var car: Car? = null,
        var phone: String? = null,
        var vkId: Long? = null,
        var facebookId: String? = null,
        var googleId: String? = null,
        var hiddenFields: List<String> = emptyList()
)