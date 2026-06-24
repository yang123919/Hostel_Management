package com.example.project.model

import java.math.BigDecimal

data class HomePageDashboardData (
    var tenantCount : Int,
    var totalRoomCount : Int,
    var occupiedRoomCount : Int,
    var revenue: BigDecimal,
) {
    init {
        if(totalRoomCount < occupiedRoomCount) {
            throw IllegalArgumentException("The number of occupied rooms cannot exceed the total number of rooms.")
        }
    }

    val occupiedPercentage: Int get() =
        if (totalRoomCount > 0)
            (occupiedRoomCount * 100) / totalRoomCount else 0

}
