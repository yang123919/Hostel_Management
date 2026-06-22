package com.example.project.Adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MockDatas.MaintenanceData
import com.example.project.R

class MaintenanceRequestAdapter(
    private var requests: MutableList<MaintenanceData>
) : RecyclerView.Adapter<MaintenanceRequestAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPhoto:       ImageView = view.findViewById(R.id.ivPhoto)
        val tvTitle:       TextView  = view.findViewById(R.id.tvTitle)
        val tvPriority:    TextView  = view.findViewById(R.id.tvPriority)
        val tvStatus:      TextView  = view.findViewById(R.id.tvStatus)
        val tvDescription: TextView  = view.findViewById(R.id.tvDescription)
        val tvReporter:    TextView  = view.findViewById(R.id.tvReporter)
        val tvDate:        TextView  = view.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.maintenance_request_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val req = requests[position]

        // Photo
        if (req.photoResId != null) {
            holder.ivPhoto.visibility = View.VISIBLE
            holder.ivPhoto.setImageResource(req.photoResId)
        } else {
            holder.ivPhoto.visibility = View.GONE
        }

        holder.tvTitle.text       = req.title
        holder.tvDescription.text = req.description
        holder.tvReporter.text    = "By ${req.reporterName}"
        holder.tvDate.text        = req.reportDate

        // Priority badge
        holder.tvPriority.text = req.priority.name
        applyBadge(holder.tvPriority, priorityColor(req.priority))

        // Status badge
        holder.tvStatus.text = formatStatus(req.status)
        applyBadge(holder.tvStatus, statusColor(req.status))
    }

    override fun getItemCount(): Int = requests.size

    fun updateList(newList: MutableList<MaintenanceData>) {
        requests = newList
        notifyDataSetChanged()
    }

    private fun applyBadge(view: TextView, color: Int) {
        val bg = GradientDrawable().apply {
            shape        = GradientDrawable.RECTANGLE
            cornerRadius = 32f
            setColor(color)
        }
        view.background = bg
        view.setTextColor(Color.WHITE)
    }

    private fun priorityColor(p: MaintenanceData.Priority) = when (p) {
        MaintenanceData.Priority.HIGH   -> Color.parseColor("#F44336")
        MaintenanceData.Priority.MEDIUM -> Color.parseColor("#FF9800")
        MaintenanceData.Priority.LOW    -> Color.parseColor("#4CAF50")
    }

    private fun statusColor(s: MaintenanceData.Status) = when (s) {
        MaintenanceData.Status.PENDING     -> Color.parseColor("#9E9E9E")
        MaintenanceData.Status.IN_PROGRESS -> Color.parseColor("#2196F3")
        MaintenanceData.Status.COMPLETED   -> Color.parseColor("#4CAF50")
    }

    private fun formatStatus(s: MaintenanceData.Status) = when (s) {
        MaintenanceData.Status.PENDING     -> "Pending"
        MaintenanceData.Status.IN_PROGRESS -> "In Progress"
        MaintenanceData.Status.COMPLETED   -> "Completed"
    }
}