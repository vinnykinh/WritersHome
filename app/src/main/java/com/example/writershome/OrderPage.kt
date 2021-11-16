package com.example.writershome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderPage : AppCompatActivity() {
    private lateinit var newOrderRecycler: RecyclerView
    private lateinit var newOrderlist: List<orderSpecs>
    private lateinit var newOrderAdapter: availableordersadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_page)


        newOrderlist = ArrayList<orderSpecs>()

        newOrderlist = arrayListOf(
            orderSpecs("#456323", "Nursing", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "PSychology", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Mental ursing", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Nursing", "Mental Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Nursing", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Medical", "HealthCare", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Nursing", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Surgery", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Nursing", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Nursing", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Nursing", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Nursing", "Public Health", "10 days left", "kes 4500"),
            orderSpecs("#456323", "Nursing", "Public Health", "10 days left", "kes 4500")
        )

        val availablenumber: TextView = findViewById(R.id.num)
        availablenumber.text = newOrderlist.count().toString() + " Available Orders"
        newOrderRecycler = findViewById(R.id.ordersrecycler)
        val linearLayoutManager = LinearLayoutManager(this)
        newOrderRecycler.layoutManager = linearLayoutManager

        newOrderAdapter = availableordersadapter(this, newOrderlist as ArrayList<orderSpecs>)
        newOrderRecycler.adapter = newOrderAdapter


    }
}