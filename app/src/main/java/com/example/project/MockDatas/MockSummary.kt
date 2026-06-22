package com.example.project.MockDatas

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

    fun getVacantRoomCount(): Int {
        return MockRoomDatas.roomList.count {
            it.isVacant
        }
    }

    fun getPendingMaintenanceCount(): Int {

        return MockMaintenanceDatas
            .maintenanceList
            .count {
                it.status != MaintenanceData.Status.COMPLETED
            }
    }

    fun getOutstandingPaymentTotal(): Double {
        return MockPaymentData.paymentList
            .filter {
                it.status != PaymentData.PaymentStatus.PAID
            }
            .sumOf {
                it.amount
            }
    }

    fun getMonthlyRevenue(): Double {
        return MockPaymentData.paymentList
            .filter {
                it.status == PaymentData.PaymentStatus.PAID
            }
            .sumOf {
                it.amount
            }
    }

    fun getCollectionRate(): Int {
        val total = MockPaymentData.paymentList.size
        if (total == 0) {
            return 0
        }

        val paid = MockPaymentData.paymentList.count {
            it.status == PaymentData.PaymentStatus.PAID
        }

        return (paid * 100) / total
    }
}
