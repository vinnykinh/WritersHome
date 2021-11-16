package com.example.writershome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import android.text.format.DateFormat.is24HourFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var dashboardRecycler: RecyclerView
    private lateinit var mlist: List<OrderCategories>
    private lateinit var mAdapter: dashBoardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mlist = ArrayList<OrderCategories>()

        mlist = arrayListOf(
            OrderCategories("Available Orders", "2", ""),
            OrderCategories("Applied Orders", "3", ""),
            OrderCategories("Confirmed Orders", "5", ""),
            OrderCategories("Clarification Orders", "6", ""),
            OrderCategories("Revision Orders", "7", ""),
            OrderCategories("Completed Orders", "234", "")

        )
        Log.e("TAG", mlist[1].toString())
        dashboardRecycler = findViewById(R.id.dashrecycler)
        val linearLayoutManager = LinearLayoutManager(this)
        dashboardRecycler.layoutManager = linearLayoutManager

        mAdapter = dashBoardAdapter(this, mlist as ArrayList<OrderCategories>)
        dashboardRecycler.adapter = mAdapter


        val date = findViewById<TextView>(R.id.time)
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        date.text = currentDate + " EAT"









    }


}