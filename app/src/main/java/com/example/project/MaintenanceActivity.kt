package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Adapters.MaintenanceRoomAdapter
import com.example.project.MockDatas.MockRoomDatas

class MaintenanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
            R.layout.activity_maintenance
        )

        val recyclerView =
            findViewById<RecyclerView>(R.id.rvRooms)

        recyclerView.layoutManager =
            LinearLayoutManager(this)

        recyclerView.adapter =
            MaintenanceRoomAdapter(
                MockRoomDatas.roomList
            )
    }
}