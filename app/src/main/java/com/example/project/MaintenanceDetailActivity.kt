package com.example.project

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Adapters.MaintenanceRequestAdapter
import com.example.project.MockDatas.MaintenanceData
import com.example.project.MockDatas.MockMaintenanceDatas
import com.example.project.MockDatas.MockRoomDatas
import com.google.android.material.chip.ChipGroup

class MaintenanceDetailActivity : AppCompatActivity() {

    private lateinit var adapter: MaintenanceRequestAdapter
    private lateinit var tvEmpty: TextView
    private var roomId: Int = -1

    // 1. Dual filter states
    private var currentStatusFilter: String = "ALL"
    private var currentPriorityFilter: String = "ALL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance_detail)

        roomId = intent.getIntExtra("ROOM_ID", -1)
        val room = MockRoomDatas.roomList.find { it.id == roomId }

        // Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title    = room?.name ?: "Maintenance Requests"
        supportActionBar?.subtitle = "${room?.floor ?: ""}  ·  ${room?.roomType?.name ?: ""}"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        tvEmpty = findViewById(R.id.tvEmpty)

        updateSummary()

        // 2. Status Filter Chips
        val chipGroupStatus = findViewById<ChipGroup>(R.id.chipGroupFilter)
        chipGroupStatus.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                currentStatusFilter = when (checkedIds[0]) {
                    R.id.chipPending    -> "PENDING"
                    R.id.chipInProgress -> "IN_PROGRESS"
                    R.id.chipCompleted  -> "COMPLETED"
                    else                -> "ALL"
                }
                refreshList()
            }
        }

        // 3. Priority Filter Chips (New)
        val chipGroupPriority = findViewById<ChipGroup>(R.id.chipGroupPriorityFilter)
        chipGroupPriority.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                currentPriorityFilter = when (checkedIds[0]) {
                    R.id.chipPriorityHigh   -> "HIGH"
                    R.id.chipPriorityMedium -> "MEDIUM"
                    R.id.chipPriorityLow    -> "LOW"
                    else                    -> "ALL"
                }
                refreshList()
            }
        }

        // RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvRequests)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MaintenanceRequestAdapter(getFilteredRequests().toMutableList())
        recyclerView.adapter = adapter
        updateEmptyState()
    }

    // 4. Combined filtering logic
    private fun getFilteredRequests(): List<MaintenanceData> {
        var filteredList = MockMaintenanceDatas.requestsForRoom(roomId)

        // Apply Status Filter
        filteredList = when (currentStatusFilter) {
            "PENDING"     -> filteredList.filter { it.status == MaintenanceData.Status.PENDING }
            "IN_PROGRESS" -> filteredList.filter { it.status == MaintenanceData.Status.IN_PROGRESS }
            "COMPLETED"   -> filteredList.filter { it.status == MaintenanceData.Status.COMPLETED }
            else          -> filteredList
        }

        // Apply Priority Filter
        filteredList = when (currentPriorityFilter) {
            "HIGH"   -> filteredList.filter { it.priority == MaintenanceData.Priority.HIGH }
            "MEDIUM" -> filteredList.filter { it.priority == MaintenanceData.Priority.MEDIUM }
            "LOW"    -> filteredList.filter { it.priority == MaintenanceData.Priority.LOW }
            else     -> filteredList
        }

        return filteredList
    }

    private fun refreshList() {
        adapter.updateList(getFilteredRequests().toMutableList())
        updateEmptyState()
    }

    private fun updateEmptyState() {
        tvEmpty.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.GONE
    }

    private fun updateSummary() {
        val all = MockMaintenanceDatas.requestsForRoom(roomId)
        findViewById<TextView>(R.id.tvTotalCount).text      = all.size.toString()
        findViewById<TextView>(R.id.tvPendingCount).text    = all.count { it.status == MaintenanceData.Status.PENDING }.toString()
        findViewById<TextView>(R.id.tvInProgressCount).text = all.count { it.status == MaintenanceData.Status.IN_PROGRESS }.toString()
        findViewById<TextView>(R.id.tvCompletedCount).text  = all.count { it.status == MaintenanceData.Status.COMPLETED }.toString()
    }
}