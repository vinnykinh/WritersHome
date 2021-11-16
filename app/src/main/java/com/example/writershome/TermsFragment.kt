package com.example.writershome

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.lang.Exception


class TermsFragment : Fragment() {
    private var backbtn: TextView? = null
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_terms, container, false)
        backbtn = view.findViewById(R.id.backbtn)
        backbtn!!.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                ?.replace(
                    R.id.frameContainer, SignUp_Fragment(),
                    Utils.SignUp_Fragment
                )?.commit()
        }
        val xrp = resources.getXml(R.drawable.text_selector)
        try {
            val csl = ColorStateList.createFromXml(
                resources,
                xrp
            )
            backbtn!!.setTextColor(csl)
        } catch (e: Exception) { }
        return view
    }
}