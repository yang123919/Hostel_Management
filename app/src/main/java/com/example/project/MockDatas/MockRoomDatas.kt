package com.example.project.MockDatas

import com.example.project.R


class MockRoomDatas {

    companion object {
        val roomList = mutableListOf(
            RoomData(
                id = 1,
                name = "Room A101",
                image = R.drawable.room_kilo,
                roomType = RoomData.RoomType.SINGLE,
                occupiedCount = 1,
                floor = "Level 1",
                monthlyRent = 500.0
            ),
            RoomData(
                id = 2,
                name = "Room A102",
                image = R.drawable.room_papa,
                roomType = RoomData.RoomType.DOUBLE,
                occupiedCount = 2,
                floor = "Level 1",
                monthlyRent = 450.0
            ),
            RoomData(
                id = 3,
                name = "Room B201",
                image = R.drawable.room_kilo,
                roomType = RoomData.RoomType.DOUBLE,
                occupiedCount = 1,
                floor = "Level 2",
                monthlyRent = 480.0
            ),
            RoomData(
                id = 4,
                name = "Room B202",
                image = R.drawable.room_papa,
                roomType = RoomData.RoomType.QUAD,
                occupiedCount = 3,
                floor = "Level 2",
                monthlyRent = 380.0
            ),
            RoomData(
                id = 5,
                name = "Room C301",
                image = R.drawable.room_kilo,
                roomType = RoomData.RoomType.SINGLE,
                occupiedCount = 0,
                floor = "Level 3",
                monthlyRent = 520.0
            ),
            RoomData(
                id = 6,
                name = "Room C302",
                image = R.drawable.room_papa,
                roomType = RoomData.RoomType.QUAD,
                occupiedCount = 1,
                floor = "Level 3",
                monthlyRent = 360.0
            )
        )
    }
}
