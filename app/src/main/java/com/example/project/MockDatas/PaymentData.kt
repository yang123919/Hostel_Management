package com.example.project.MockDatas

data class PaymentData(
    val tenantId: Int,
    val tenantName: String,
    val roomId: Int,
    val month: String,
    val amount: Double,
    var status: PaymentStatus,
    val dueDate: String,
    var paidDate: String? = null
) {

    enum class PaymentStatus {
        PAID,
        UNPAID,
        OVERDUE
    }
}
