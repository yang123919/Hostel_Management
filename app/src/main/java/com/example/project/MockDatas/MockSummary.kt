package com.example.project.MockDatas

import com.example.project.model.HomePageDashboardData
import com.example.project.model.PaymentData

object MockSummary {

    fun getDashboardData(): HomePageDashboardData {

        val totalRooms = MockRoomDatas.roomList.size

        val occupiedRooms =
            MockRoomDatas.roomList.count {
                it.occupiedCount > 0
            }

        val tenantCount =
            MockTenantDatas.tenantList.size


        val revenue =
            MockPaymentData.paymentList
                .filter {
                    it.status == PaymentData.PaymentStatus.PAID
                }
                .sumOf {
                    it.amount
                }

        return HomePageDashboardData(
            tenantCount,
            totalRooms,
            occupiedRooms,
            revenue.toString().toBigDecimal()
        )
    }
}