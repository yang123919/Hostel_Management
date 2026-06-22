package com.example.project.MockDatas

data class MaintenanceData(
    val id: Int,
    val roomId: Int,
    val title: String,
    val description: String,
    var priority: Priority,
    var status: Status,
    val reporterName: String,
    val reportDate: String,
    val photoResId: Int? = null
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
