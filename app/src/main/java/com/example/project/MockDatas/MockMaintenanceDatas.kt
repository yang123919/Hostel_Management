package com.example.project.MockDatas

import com.example.project.R

class MockMaintenanceDatas {

    companion object {
        val maintenanceList = mutableListOf(

            MaintenanceData(
                id = 1,
                roomId = 1,
                title = "Air Conditioner",
                description = "Aircond not cooling during the afternoon.",
                priority = MaintenanceData.Priority.HIGH,
                status = MaintenanceData.Status.PENDING,
                reporterName = "John Tan",
                reportDate = "15 Jun 2026",
                photoResId = R.drawable.room_kilo
            ),

            MaintenanceData(
                id = 2,
                roomId = 1,
                title = "Ceiling Light",
                description = "Main ceiling light flickers and needs checking.",
                priority = MaintenanceData.Priority.MEDIUM,
                status = MaintenanceData.Status.PENDING,
                reporterName = "John Tan",
                reportDate = "17 Jun 2026",
                photoResId = R.drawable.room_papa
            ),

            MaintenanceData(
                id = 3,
                roomId = 2,
                title = "Toilet Leak",
                description = "Water leaking near the toilet pipe.",
                priority = MaintenanceData.Priority.HIGH,
                status = MaintenanceData.Status.IN_PROGRESS,
                reporterName = "Sarah Lim",
                reportDate = "16 Jun 2026",
                photoResId = R.drawable.room_papa
            ),

            MaintenanceData(
                id = 4,
                roomId = 3,
                title = "Broken Door",
                description = "Door lock damaged after check-in.",
                priority = MaintenanceData.Priority.MEDIUM,
                status = MaintenanceData.Status.COMPLETED,
                reporterName = "Alicia Wong",
                reportDate = "10 Jun 2026",
                photoResId = R.drawable.room_kilo
            ),

            MaintenanceData(
                id = 5,
                roomId = 4,
                title = "Loose Bed Frame",
                description = "Upper bunk frame makes noise when moving.",
                priority = MaintenanceData.Priority.LOW,
                status = MaintenanceData.Status.PENDING,
                reporterName = "Raj Kumar",
                reportDate = "18 Jun 2026",
                photoResId = R.drawable.room_papa
            )
        )

        fun openRequests(): List<MaintenanceData> {
            return maintenanceList.filter {
                it.status != MaintenanceData.Status.COMPLETED
            }
        }

        fun requestsForRoom(roomId: Int): List<MaintenanceData> {
            return maintenanceList.filter {
                it.roomId == roomId
            }
        }

        fun openRequestCountForRoom(roomId: Int): Int {
            return requestsForRoom(roomId).count {
                it.status != MaintenanceData.Status.COMPLETED
            }
        }

        fun addRequest(
            roomId: Int,
            title: String,
            description: String,
            photoResId: Int?
        ) {
            val nextId = (maintenanceList.maxOfOrNull { it.id } ?: 0) + 1
            maintenanceList.add(
                0,
                MaintenanceData(
                    id = nextId,
                    roomId = roomId,
                    title = title,
                    description = description,
                    priority = MaintenanceData.Priority.MEDIUM,
                    status = MaintenanceData.Status.PENDING,
                    reporterName = "Tenant App",
                    reportDate = "19 Jun 2026",
                    photoResId = photoResId
                )
            )
        }
    }
}
