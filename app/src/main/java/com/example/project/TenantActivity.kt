package com.example.project

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Adapters.TenantAdapter
import com.example.project.MockDatas.MockTenantDatas
import com.example.project.model.TenantData
import com.google.android.material.chip.ChipGroup

class TenantActivity : AppCompatActivity() {

    private lateinit var adapter: TenantAdapter

    private var currentFilter: TenantData.TenantStatus? = null
    private var currentSearch = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tenants & Rooms"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        updateSummary()

        val recyclerView = findViewById<RecyclerView>(R.id.rvTenants)

        adapter = TenantAdapter(
            MockTenantDatas.tenantList.toMutableList()
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        setupSearch()
        setupFilters()
    }

    private fun setupSearch() {

        val searchView = findViewById<SearchView>(R.id.searchView)

        searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    currentSearch = newText ?: ""

                    applyFilters()

                    return true
                }
            }
        )
    }

    private fun setupFilters() {

        val chipGroup = findViewById<ChipGroup>(R.id.chipGroupFilter)

        chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->

            currentFilter = when {

                checkedIds.contains(R.id.chipActive) ->
                    TenantData.TenantStatus.ACTIVE

                checkedIds.contains(R.id.chipCheckedOut) ->
                    TenantData.TenantStatus.CHECKED_OUT

                else -> null
            }

            applyFilters()
        }
    }

    private fun applyFilters() {

        val filteredList = MockTenantDatas.tenantList.filter { tenant ->

            val matchesStatus =
                currentFilter == null ||
                        tenant.status == currentFilter

            val matchesSearch =
                tenant.name.contains(
                    currentSearch,
                    ignoreCase = true
                )

            matchesStatus && matchesSearch
        }

        adapter.updateList(filteredList.toMutableList())
    }

    private fun updateSummary() {

        val all = MockTenantDatas.tenantList

        findViewById<TextView>(R.id.tvTotalCount).text =
            all.size.toString()

        findViewById<TextView>(R.id.tvActiveCount).text =
            all.count {
                it.status == TenantData.TenantStatus.ACTIVE
            }.toString()

        findViewById<TextView>(R.id.tvCheckedOutCount).text =
            all.count {
                it.status == TenantData.TenantStatus.CHECKED_OUT
            }.toString()
    }
}