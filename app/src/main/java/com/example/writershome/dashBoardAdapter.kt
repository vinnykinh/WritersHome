package com.example.writershome

import android.R.attr
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import android.R.attr.button




class dashBoardAdapter(
    private var mContext: Context,
    private var mList: List<OrderCategories>
) : RecyclerView.Adapter<dashBoardAdapter.dashBardViewHolder>() {

    inner class dashBardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val order_number: TextView = itemView.findViewById(R.id.new_orders_number)
        val order_type: TextView = itemView.findViewById(R.id.order_type)
        val relayout: RelativeLayout =itemView.findViewById(R.id.relayout)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dashBardViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.dashboard_card, parent, false)
        return dashBardViewHolder(view)
    }

    override fun onBindViewHolder(holder: dashBardViewHolder, position: Int) {
        val current = mList[position]
        holder.order_number.text = current.getorder_number()
        holder.order_type.text = current.getorder_type()

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, OrderPage::class.java)
            mContext.startActivity(intent)
        }
        val array: Array<String> = mContext.resources.getStringArray(R.array.rainbow)


            val random = Random()
            val color =
                Color.argb(240, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            holder.relayout.setBackgroundColor(color)



}

    override fun getItemCount(): Int {
        return mList.size
    }

}