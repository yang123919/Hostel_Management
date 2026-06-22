package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.MockDatas.MockHomeData
import com.example.project.MockDatas.MockSummary

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val dashboard = MockSummary.getDashboardData()

        findViewById<TextView>(R.id.tvTenantCount).text =
            "Total Tenants: ${dashboard.tenantCount}"

        findViewById<TextView>(R.id.tvRoomCount).text =
            "Total Rooms: ${dashboard.totalRoomCount}"

        findViewById<TextView>(R.id.tvOccupiedRooms).text =
            "Occupied Rooms: ${dashboard.occupiedRoomCount}"

        findViewById<TextView>(R.id.tvOccupancyRate).text =
            "Occupancy Rate: ${dashboard.occupiedPercentage}%"

        findViewById<TextView>(R.id.tvRevenue).text =
            "RM ${dashboard.revenue}"


        findViewById<Button>(R.id.btnMaintenance)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        MaintenanceActivity::class.java
                    )
                )
            }

        findViewById<Button>(R.id.btnPayments)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        PaymentActivity::class.java
                    )
                )
            }

        findViewById<Button>(R.id.btnTenants)
            .setOnClickListener {

                startActivity(
                    Intent(
                        this,
                        TenantActivity::class.java
                    )
                )
            }
    }
}