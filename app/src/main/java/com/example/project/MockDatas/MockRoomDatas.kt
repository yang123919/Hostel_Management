package com.example.project.MockDatas

import com.example.project.R
import com.example.project.model.RoomData


class MockRoomDatas {

    companion object {
        val roomList = mutableListOf(
            RoomData(
                id = 1,
                name = "Room Papa",
                image = R.drawable.room_papa,
                roomType = RoomData.RoomType.DOUBLE,
                occupiedCount = 2,
                monthlyRent = 500.0
            ),
            RoomData(
                id = 2,
                name = "Room Kilo",
                image = R.drawable.room_kilo,
                roomType = RoomData.RoomType.QUAD,
                occupiedCount = 4,
                monthlyRent = 250.0
            ),

        )
    }
}
