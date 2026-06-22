package com.example.project.Adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MockDatas.MockRoomDatas
import com.example.project.MockDatas.TenantData
import com.example.project.R

class TenantAdapter(
    private val tenants: List<TenantData>
) : RecyclerView.Adapter<TenantAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName:       TextView = view.findViewById(R.id.tvName)
        val tvRoomName:   TextView = view.findViewById(R.id.tvRoomName)
        val tvPhone:      TextView = view.findViewById(R.id.tvPhone)
        val tvEmail:      TextView = view.findViewById(R.id.tvEmail)
        val tvCheckIn:    TextView = view.findViewById(R.id.tvCheckIn)
        val tvCheckOut:   TextView = view.findViewById(R.id.tvCheckOut)
        val tvStatus:     TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tenant_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val t    = tenants[position]
        val room = MockRoomDatas.roomList.find { it.id == t.roomId }

        holder.tvName.text     = t.name
        holder.tvRoomName.text = room?.name ?: "Room ${t.roomId}"
        holder.tvPhone.text    = t.phone
        holder.tvEmail.text    = t.email
        holder.tvCheckIn.text  = "Check-in: ${t.checkInDate}"

        if (t.checkOutDate != null) {
            holder.tvCheckOut.visibility = View.VISIBLE
            holder.tvCheckOut.text       = "Check-out: ${t.checkOutDate}"
        } else {
            holder.tvCheckOut.visibility = View.GONE
        }

        holder.tvStatus.text = formatStatus(t.status)
        applyBadge(holder.tvStatus, statusColor(t.status))
    }

    override fun getItemCount(): Int = tenants.size

    private fun applyBadge(view: TextView, color: Int) {
        val bg = GradientDrawable().apply {
            shape        = GradientDrawable.RECTANGLE
            cornerRadius = 32f
            setColor(color)
        }
        view.background = bg
        view.setTextColor(Color.WHITE)
    }

    private fun statusColor(s: TenantData.TenantStatus) = when (s) {
        TenantData.TenantStatus.ACTIVE      -> Color.parseColor("#4CAF50")
        TenantData.TenantStatus.CHECKED_OUT -> Color.parseColor("#9E9E9E")
    }

    private fun formatStatus(s: TenantData.TenantStatus) = when (s) {
        TenantData.TenantStatus.ACTIVE      -> "Active"
        TenantData.TenantStatus.CHECKED_OUT -> "Checked Out"
    }
}