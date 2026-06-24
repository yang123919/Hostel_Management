package com.example.project

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Adapters.MaintenanceRequestAdapter
import com.example.project.model.MaintenanceData
import com.example.project.MockDatas.MockMaintenanceDatas
import com.example.project.MockDatas.MockRoomDatas
import com.google.android.material.chip.ChipGroup

class MaintenanceDetailActivity : AppCompatActivity() {

    private lateinit var adapter: MaintenanceRequestAdapter
    private lateinit var tvEmpty: TextView
    private var roomId: Int = -1

    // State is now tracked using your actual Enums. 'null' means no filter (ALL).
    private var currentStatusFilter: MaintenanceData.Status? = null
    private var currentPriorityFilter: MaintenanceData.Priority? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maintenance_detail)

        roomId = intent.getIntExtra("ROOM_ID", -1)
        val room = MockRoomDatas.roomList.find { it.id == roomId }

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title    = room?.name ?: "Maintenance Requests"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        tvEmpty = findViewById(R.id.tvEmpty)

        updateSummary()

        val chipGroupStatus = findViewById<ChipGroup>(R.id.chipGroupFilter)
        chipGroupStatus.setOnCheckedStateChangeListener { _, checkedIds ->
            println("Checked Status IDs: $checkedIds")
            if (checkedIds.isNotEmpty()) {
                currentStatusFilter = when (checkedIds[0]) {
                    R.id.chipPending    -> MaintenanceData.Status.PENDING
                    R.id.chipInProgress -> MaintenanceData.Status.IN_PROGRESS
                    R.id.chipCompleted  -> MaintenanceData.Status.COMPLETED
                    else                -> null
                }
                refreshList()
            }
        }

        val chipGroupPriority = findViewById<ChipGroup>(R.id.chipGroupPriorityFilter)
        chipGroupPriority.setOnCheckedStateChangeListener { _, checkedIds ->
            println("Checked Priority IDs: $checkedIds")
            if (checkedIds.isNotEmpty()) {
                currentPriorityFilter = when (checkedIds[0]) {
                    R.id.chipPriorityHigh   -> MaintenanceData.Priority.HIGH
                    R.id.chipPriorityMedium -> MaintenanceData.Priority.MEDIUM
                    R.id.chipPriorityLow    -> MaintenanceData.Priority.LOW
                    else                    -> null
                }
                refreshList()
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvRequests)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MaintenanceRequestAdapter(getFilteredRequests().toMutableList())
        recyclerView.adapter = adapter
        updateEmptyState()
    }

    private fun getFilteredRequests(): List<MaintenanceData> {
        var filteredList = MockMaintenanceDatas.requestsForRoom(roomId)

        // If a status filter is selected, filter by it. Otherwise, let everything pass.
        currentStatusFilter?.let { status ->
            filteredList = filteredList.filter { it.status == status }
        }

        // If a priority filter is selected, filter by it. Otherwise, let everything pass.
        currentPriorityFilter?.let { priority ->
            filteredList = filteredList.filter { it.priority == priority }
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