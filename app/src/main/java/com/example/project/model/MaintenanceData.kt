package com.example.project.model

data class MaintenanceData(
    val id: Int,
    val roomId: Int,
    val tenantId: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val status: Status,
    val reportDate: String,
    val photoResId: Int?=null
) {

    enum class Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    enum class Status {
        PENDING,
        IN_PROGRESS,
        COMPLETED
    }
}
