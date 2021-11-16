package com.example.writershome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.writershome.R
import android.view.View
import com.example.writershome.Utils.Login_Fragment

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


class LoginActivity : AppCompatActivity() {
    val fragmentManager = supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)






        // If savedinstnacestate is null then replace login fragment

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                .beginTransaction()
                .replace(
                    R.id.frameContainer, Login_Fragment(),
                    Utils.Login_Fragment
                ).commit()
        }

        // On close icon click finish activity

        // On close icon click finish activity
        val gif = findViewById<View>(R.id.close_activity)
        Glide.with(this).load(R.drawable.login).into(gif as ImageView)



    }

     //Replace Login Fragment with animation
    @SuppressLint("ResourceType")
    fun replaceLoginFragment() {
        fragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
            .replace(
                R.id.frameContainer, Login_Fragment(),
                Utils.Login_Fragment
            ).commit()
    }
    override fun onBackPressed() {

        // Find the tag of signup and forgot password fragment
        val SignUp_Fragment: Fragment? = fragmentManager
            .findFragmentByTag(Utils.SignUp_Fragment)
        val ForgotPassword_Fragment: Fragment? = fragmentManager
            .findFragmentByTag(Utils.ForgotPassword_Fragment)

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task
        if (SignUp_Fragment != null)  else if (ForgotPassword_Fragment != null) replaceLoginFragment() else super.onBackPressed()
    }





}