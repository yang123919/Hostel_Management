package com.example.project.MockDatas

import com.example.project.model.PaymentData


class MockPaymentData {

    companion object {
        val paymentList = mutableListOf(

            PaymentData(
                tenantId = 1,
                tenantName = "Kangjie",
                roomId = 1,
                month = "June 2026",
                amount = 500.0,
                status = PaymentData.PaymentStatus.PAID,
                dueDate = "05 Jun 2026",
                paidDate = "03 Jun 2026"
            ),

            PaymentData(
                tenantId = 2,
                tenantName = "Qirun",
                roomId = 2,
                month = "June 2026",
                amount = 250.0,
                status = PaymentData.PaymentStatus.PAID,
                dueDate = "05 Jun 2026",
                paidDate = "05 Jun 2026"
            ),

            PaymentData(
                tenantId = 3,
                tenantName = "Rentai",
                roomId = 2,
                month = "June 2026",
                amount = 250.0,
                status = PaymentData.PaymentStatus.OVERDUE,
                dueDate = "05 Jun 2026"
            ),

            PaymentData(
                tenantId = 4,
                tenantName = "Dominic",
                roomId = 3,
                month = "June 2026",
                amount = 500.0,
                status = PaymentData.PaymentStatus.UNPAID,
                dueDate = "25 Jun 2026"
            ),

            PaymentData(
                tenantId = 5,
                tenantName = "Junxiang",
                roomId = 4,
                month = "June 2026",
                amount = 250.0,
                status = PaymentData.PaymentStatus.PAID,
                dueDate = "05 Jun 2026",
                paidDate = "01 Jun 2026"
            ),

            PaymentData(
                tenantId = 6,
                tenantName = "Piriyan",
                roomId = 4,
                month = "June 2026",
                amount = 250.0,
                status = PaymentData.PaymentStatus.OVERDUE,
                dueDate = "05 Jun 2026"
            )
        )
    }
}
