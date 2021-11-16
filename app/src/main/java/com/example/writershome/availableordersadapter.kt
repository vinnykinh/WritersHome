package com.example.writershome

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.transition.Slide
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionManager
import java.util.*
import android.content.DialogInterface

import android.R.layout
import android.app.AlertDialog
import android.view.LayoutInflater





class availableordersadapter (private var mContext: Context,
                              private var mList: List<orderSpecs>):RecyclerView.Adapter<availableordersadapter.availableordersViewHolder>() {

    inner class availableordersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val orderID: TextView = itemView.findViewById(R.id.orderID)
        val subject: TextView = itemView.findViewById(R.id.subject)
        val taskTitle: TextView = itemView.findViewById(R.id.task_Title)
        val deadline: TextView = itemView.findViewById(R.id.deadline)
        val pay: TextView = itemView.findViewById(R.id.pay)
        val applyBTN: Button = itemView.findViewById(R.id.apply)
        val newlayout:RelativeLayout=itemView.findViewById(R.id.newlayout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): availableordersViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.new_orders_card, parent, false)
        return availableordersViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: availableordersViewHolder, position: Int) {
        val current = mList[position]
        holder.orderID.text =current.getOrderID()
        holder.subject.text ="Subject: "+current.getSubject()
        holder.taskTitle.text="Task Title: "+current.getTaskTitle()
        holder.deadline.text= "Deadline: "+current.getDeadline()
        holder.pay.text ="Pay: "+ current.getPay()
        holder.newlayout.setOnClickListener {


//            if (username.text.isNotEmpty()) {
//                val user = username.text.toString()
//
//                App.user = user
//                startActivity(Intent(this@MainActivity, ChatActivity::class.java))
//            } else {
//                Toast.makeText(applicationContext,"Username should not be empty", Toast.LENGTH_SHORT).show()
//            }

            val user = "username.text.toString()"

           App.user = user




            mContext.startActivity(Intent(mContext,ViewOrderActivity::class.java))
        }
        holder.applyBTN.setOnClickListener {

            val inflater:LayoutInflater = mContext.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view =   inflater.inflate(R.layout.applyorder_popup, null)
            val alertbox: AlertDialog.Builder = AlertDialog.Builder(view.rootView.context)
            alertbox.setTitle("Apply Order")
            alertbox.setView(view)
            alertbox.setPositiveButton(
                "Apply",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel()



                })
            alertbox.setNegativeButton(
                "Close",
                DialogInterface.OnClickListener { dialog, id -> dialog.dismiss()



                })

            alertbox.show()

        }


        val random = Random()
        val color =
            Color.argb(240, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        holder.newlayout.setBackgroundColor(color)

    }

    override fun getItemCount(): Int =mList.size




    }



