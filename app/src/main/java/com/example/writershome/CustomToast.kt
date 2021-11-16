package com.example.writershome

import android.widget.Toast

import android.view.Gravity

import com.example.writershome.R
import android.content.Context

import android.widget.TextView

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View


class CustomToast {
    // Custom Toast Method
    fun Show_Toast(context: Context, view: View, error: String?) {

        // Layout Inflater for inflating custom view
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        // inflate the layout over view
        val layout: View = inflater.inflate(
            R.layout.custom_toast,
            view.findViewById(R.id.toast_root) as ViewGroup
        )

        // Get TextView id and set error
        val text = layout.findViewById(R.id.toast_error) as TextView
        text.text = error
        val toast = Toast(context) // Get Toast Context
        toast.setGravity(Gravity.TOP or Gravity.FILL_HORIZONTAL, 0, 0) // Set
        // Toast
        // gravity
        // and
        // Fill
        // Horizoontal
        toast.duration = Toast.LENGTH_SHORT // Set Duration
        toast.view = layout // Set Custom View over toast
        toast.show() // Finally show toast
    }
}