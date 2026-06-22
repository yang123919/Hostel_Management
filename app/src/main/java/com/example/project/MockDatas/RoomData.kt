package com.example.project.MockDatas


data class RoomData(
    val id: Int,
    val name: String,
    val image: Int,
    val roomType: RoomType,
    var occupiedCount: Int,
    val floor: String,
    val monthlyRent: Double
) {
    enum class RoomType {
        SINGLE,
        DOUBLE,
        QUAD
    }
    val capacity: Int
        get() = if (roomType == RoomType.SINGLE) {
            1
        } else if (roomType == RoomType.DOUBLE) {
            2
        } else {
            4
        }

    val availableBeds: Int
        get() = capacity - occupiedCount

    val isVacant: Boolean
        get() = occupiedCount == 0
}
