package com.example.project.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MockDatas.MockRoomDatas
import com.example.project.model.PaymentData
import com.example.project.R
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.content.ContextCompat

class PaymentAdapter(
    private var payments: MutableList<PaymentData>
) : RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTenantName: TextView = view.findViewById(R.id.tvTenantName)
        val tvRoomName:   TextView = view.findViewById(R.id.tvRoomName)
        val tvMonth:      TextView = view.findViewById(R.id.tvMonth)
        val tvAmount:     TextView = view.findViewById(R.id.tvAmount)
        val tvStatus:     TextView = view.findViewById(R.id.tvStatus)
        val tvDueDate:    TextView = view.findViewById(R.id.tvDueDate)
        val tvPaidDate:   TextView = view.findViewById(R.id.tvPaidDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.payment_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p    = payments[position]
        val room = MockRoomDatas.roomList.find { it.id == p.roomId }

        holder.tvTenantName.text = p.tenantName
        holder.tvRoomName.text   = room?.name ?: "Room ${p.roomId}"
        holder.tvMonth.text      = p.month
        holder.tvAmount.text     = "RM %.2f".format(p.amount)
        holder.tvDueDate.text    = "Due: ${p.dueDate}"

        holder.tvStatus.text     = formatStatus(p.status)

        if (p.paidDate != null) {
            holder.tvPaidDate.visibility = View.VISIBLE
            holder.tvPaidDate.text       = "Paid: ${p.paidDate}"
        } else {
            holder.tvPaidDate.visibility = View.GONE
        }

        val drawable = ContextCompat.getDrawable(
            holder.itemView.context,
            R.drawable.badge_shape
        )!!.mutate()

        DrawableCompat.setTint(
            drawable,
            when (p.status) {
                PaymentData.PaymentStatus.PAID -> Color.parseColor("#4CAF50")
                PaymentData.PaymentStatus.UNPAID -> Color.parseColor("#FF9800")
                PaymentData.PaymentStatus.OVERDUE -> Color.parseColor("#F44336")
            }
        )

        holder.tvStatus.background = drawable
    }

    override fun getItemCount(): Int = payments.size

    fun updateList(newList: MutableList<PaymentData>) {
        payments = newList
        notifyDataSetChanged()
    }

    private fun formatStatus(s: PaymentData.PaymentStatus) = when (s) {
        PaymentData.PaymentStatus.PAID    -> "Paid"
        PaymentData.PaymentStatus.UNPAID  -> "Unpaid"
        PaymentData.PaymentStatus.OVERDUE -> "Overdue"
    }
}