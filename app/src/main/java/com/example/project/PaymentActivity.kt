package com.example.project

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Adapters.PaymentAdapter
import com.example.project.MockDatas.MockPaymentData
import com.example.project.MockDatas.PaymentData
import com.google.android.material.chip.ChipGroup

class PaymentActivity : AppCompatActivity() {

    private lateinit var adapter: PaymentAdapter
    private lateinit var tvEmpty: TextView
    private var currentFilter: String = "ALL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Rent & Payments"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        tvEmpty = findViewById(R.id.tvEmpty)
        updateSummary()

        val chipGroup = findViewById<ChipGroup>(R.id.chipGroupFilter)
        chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            if (checkedIds.isNotEmpty()) {
                currentFilter = when (checkedIds[0]) {
                    R.id.chipPaid    -> "PAID"
                    R.id.chipUnpaid  -> "UNPAID"
                    R.id.chipOverdue -> "OVERDUE"
                    else             -> "ALL"
                }
                refreshList()
            }
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rvPayments)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PaymentAdapter(getFilteredPayments().toMutableList())
        recyclerView.adapter = adapter
        updateEmptyState()
    }

    private fun getFilteredPayments(): List<PaymentData> {
        val all = MockPaymentData.paymentList
        return when (currentFilter) {
            "PAID"    -> all.filter { it.status == PaymentData.PaymentStatus.PAID }
            "UNPAID"  -> all.filter { it.status == PaymentData.PaymentStatus.UNPAID }
            "OVERDUE" -> all.filter { it.status == PaymentData.PaymentStatus.OVERDUE }
            else      -> all
        }
    }

    private fun refreshList() {
        adapter.updateList(getFilteredPayments().toMutableList())
        updateEmptyState()
    }

    private fun updateEmptyState() {
        tvEmpty.visibility = if (adapter.itemCount == 0) View.VISIBLE else View.GONE
    }

    private fun updateSummary() {
        val all          = MockPaymentData.paymentList
        val paid         = all.filter { it.status == PaymentData.PaymentStatus.PAID }
        val totalRevenue = paid.sumOf { it.amount }
        val outstanding  = all.filter { it.status != PaymentData.PaymentStatus.PAID }.sumOf { it.amount }
        val total        = all.sumOf { it.amount }
        val rate         = if (total > 0) (totalRevenue / total * 100).toInt() else 0

        findViewById<TextView>(R.id.tvRevenue).text        = "RM %.2f".format(totalRevenue)
        findViewById<TextView>(R.id.tvOutstanding).text    = "RM %.2f".format(outstanding)
        findViewById<TextView>(R.id.tvCollectionRate).text = "$rate%"
        findViewById<TextView>(R.id.tvPaidCount).text      = paid.size.toString()
        findViewById<TextView>(R.id.tvUnpaidCount).text    = all.count { it.status == PaymentData.PaymentStatus.UNPAID }.toString()
        findViewById<TextView>(R.id.tvOverdueCount).text   = all.count { it.status == PaymentData.PaymentStatus.OVERDUE }.toString()
    }
}