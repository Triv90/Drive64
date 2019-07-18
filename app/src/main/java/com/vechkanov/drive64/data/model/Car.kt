package com.vechkanov.drive64.data.model

data class Car(
        private var model: String,
        private var make: String,
        private var freeSeats: Int,
        private var freeBaggage: Boolean,
        private var colorName: String,
        private var colorCode: Int
)
