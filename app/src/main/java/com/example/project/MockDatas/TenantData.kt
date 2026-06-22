package com.example.project.MockDatas


data class TenantData(
    val id: Int,
    val name: String,
    val roomId: Int,
    val phone: String,
    val email: String,
    val checkInDate: String,
    val checkOutDate: String? = null,
    val status: TenantStatus = TenantStatus.ACTIVE
) {
    enum class TenantStatus {
        ACTIVE,
        CHECKED_OUT
    }
}
