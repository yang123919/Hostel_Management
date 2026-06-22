package com.example.project.MockDatas


class MockPaymentData {

    companion object {
        val paymentList = mutableListOf(

            PaymentData(
                tenantId = 1,
                tenantName = "John Tan",
                roomId = 1,
                month = "June 2026",
                amount = 500.0,
                status = PaymentData.PaymentStatus.PAID,
                dueDate = "05 Jun 2026",
                paidDate = "03 Jun 2026"
            ),

            PaymentData(
                tenantId = 2,
                tenantName = "Sarah Lim",
                roomId = 2,
                month = "June 2026",
                amount = 450.0,
                status = PaymentData.PaymentStatus.PAID,
                dueDate = "05 Jun 2026",
                paidDate = "05 Jun 2026"
            ),

            PaymentData(
                tenantId = 3,
                tenantName = "Michael Lee",
                roomId = 2,
                month = "June 2026",
                amount = 450.0,
                status = PaymentData.PaymentStatus.OVERDUE,
                dueDate = "05 Jun 2026"
            ),

            PaymentData(
                tenantId = 4,
                tenantName = "Alicia Wong",
                roomId = 3,
                month = "June 2026",
                amount = 480.0,
                status = PaymentData.PaymentStatus.UNPAID,
                dueDate = "25 Jun 2026"
            ),

            PaymentData(
                tenantId = 5,
                tenantName = "Nur Aisyah",
                roomId = 4,
                month = "June 2026",
                amount = 380.0,
                status = PaymentData.PaymentStatus.PAID,
                dueDate = "05 Jun 2026",
                paidDate = "01 Jun 2026"
            ),

            PaymentData(
                tenantId = 6,
                tenantName = "Raj Kumar",
                roomId = 4,
                month = "June 2026",
                amount = 380.0,
                status = PaymentData.PaymentStatus.OVERDUE,
                dueDate = "05 Jun 2026"
            ),

            PaymentData(
                tenantId = 7,
                tenantName = "Chen Wei",
                roomId = 4,
                month = "June 2026",
                amount = 380.0,
                status = PaymentData.PaymentStatus.UNPAID,
                dueDate = "25 Jun 2026"
            ),

            PaymentData(
                tenantId = 8,
                tenantName = "Farah Noor",
                roomId = 6,
                month = "June 2026",
                amount = 360.0,
                status = PaymentData.PaymentStatus.PAID,
                dueDate = "05 Jun 2026",
                paidDate = "02 Jun 2026"
            )
        )

    }
}
