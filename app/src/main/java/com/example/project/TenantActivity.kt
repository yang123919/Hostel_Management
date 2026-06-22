package com.example.project

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Adapters.TenantAdapter
import com.example.project.MockDatas.MockTenantDatas
import com.example.project.MockDatas.TenantData

class TenantActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tenant)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Tenants & Rooms"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        updateSummary()

        val recyclerView = findViewById<RecyclerView>(R.id.rvTenants)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TenantAdapter(MockTenantDatas.tenantList)
    }

    private fun updateSummary() {
        val all = MockTenantDatas.tenantList
        findViewById<TextView>(R.id.tvTotalCount).text      = all.size.toString()
        findViewById<TextView>(R.id.tvActiveCount).text     = all.count { it.status == TenantData.TenantStatus.ACTIVE }.toString()
        findViewById<TextView>(R.id.tvCheckedOutCount).text = all.count { it.status == TenantData.TenantStatus.CHECKED_OUT }.toString()
    }
}