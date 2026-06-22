package com.example.project.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MockDatas.MockMaintenanceDatas
import com.example.project.MockDatas.RoomData
import com.example.project.R
import com.example.project.MaintenanceDetailActivity

class MaintenanceRoomAdapter(
    private val rooms: List<RoomData>
) : RecyclerView.Adapter<MaintenanceRoomAdapter.ViewHolder>() {

    inner class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val roomName: TextView =
            view.findViewById(R.id.tvRoomName)

        val floor: TextView =
            view.findViewById(R.id.tvFloor)

        val roomType: TextView =
            view.findViewById(R.id.tvRoomType)

        val badge: TextView =
            view.findViewById(R.id.tvBadge)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.maintenance_room_card,
                parent,
                false
            )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val room = rooms[position]

        holder.roomName.text = room.name
        holder.floor.text = room.floor
        holder.roomType.text = room.roomType.name

        val count =
            MockMaintenanceDatas
                .openRequestCountForRoom(room.id)

        holder.badge.text = count.toString()

        if(count == 0){
            holder.badge.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                MaintenanceDetailActivity::class.java
            )

            intent.putExtra(
                "ROOM_ID",
                room.id
            )

            holder.itemView.context
                .startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return rooms.size
    }
}