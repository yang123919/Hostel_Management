package com.example.project.MockDatas

import com.example.project.R
import com.example.project.model.MaintenanceData

class MockMaintenanceDatas {

    companion object {
        val maintenanceList = mutableListOf(

            MaintenanceData(
                id = 1,
                roomId = 1,
                tenantId = 1,
                title = "Air Conditioner",
                description = "Aircond not cooling during the afternoon.",
                priority = MaintenanceData.Priority.HIGH,
                status = MaintenanceData.Status.PENDING,
                reportDate = "15 Jun 2026"
            ),
            MaintenanceData(
                id = 2,
                roomId = 1,
                tenantId = 1,
                title = "Bathroom Sink Leak",
                description = "Water leaking under sink pipe.",
                priority = MaintenanceData.Priority.MEDIUM,
                status = MaintenanceData.Status.IN_PROGRESS,
                reportDate = "16 Jun 2026",
                photoResId = R.drawable.room_kilo
            ),

            MaintenanceData(
                id = 3,
                roomId = 2,
                tenantId = 2,
                title = "Ceiling Light Flicker",
                description = "Light keeps flickering at night.",
                priority = MaintenanceData.Priority.MEDIUM,
                status = MaintenanceData.Status.PENDING,
                reportDate = "17 Jun 2026",
                photoResId = R.drawable.room_papa
            ),

            MaintenanceData(
                id = 4,
                roomId = 2,
                tenantId = 3,
                title = "Toilet Blocked",
                description = "Toilet is clogged and slow draining.",
                priority = MaintenanceData.Priority.HIGH,
                status = MaintenanceData.Status.IN_PROGRESS,
                reportDate = "18 Jun 2026",
                photoResId = R.drawable.room_papa
            ),

            MaintenanceData(
                id = 5,
                roomId = 2,
                tenantId = 4,
                title = "Door Lock Broken",
                description = "Room door lock cannot close properly.",
                priority = MaintenanceData.Priority.HIGH,
                status = MaintenanceData.Status.COMPLETED,
                reportDate = "10 Jun 2026",
                photoResId = R.drawable.room_kilo
            ),


        )

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
    }
}
