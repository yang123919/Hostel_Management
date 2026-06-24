package com.example.project.MockDatas

import com.example.project.model.TenantData


class MockTenantDatas {

    companion object {
        val tenantList = mutableListOf(

            TenantData(
                1,
                "Kangjie",
                1,
                "0123456789",
                "kangjie@example.com",
                "01 Jan 2026"
            ),

            TenantData(
                2,
                "Qirun",
                2,
                "0112233445",
                "qirun@example.com",
                "05 Jan 2026"
            ),

            TenantData(
                3,
                "Rentai",
                2,
                "0178877665",
                "rentai@example.com",
                "10 Jan 2026"
            ),

            TenantData(
                4,
                "Dominic",
                1,
                "0169988776",
                "dominic@example.com",
                "15 Feb 2026"
            ),

            TenantData(
                5,
                "Junxiang",
                2,
                "0134455667",
                "junxiang@example.com",
                "20 Feb 2026"
            ),

            TenantData(
                6,
                "Piriyan",
                2,
                "0186677889",
                "ryan@example.com",
                "01 Mar 2026"
            ),

            TenantData(
                7,
                "Ee Szen",
                2,
                "0186677889",
                "eeszen@example.com",
                "01 Mar 2025",
                "01 Feb 2026",
                status = TenantData.TenantStatus.CHECKED_OUT
            )
        )
        fun getTenantById(id: Int): TenantData? {
            return tenantList.find { it.id == id }
        }
    }
}
