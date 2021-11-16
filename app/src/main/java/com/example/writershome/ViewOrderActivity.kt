package com.example.writershome

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle



import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import javax.security.auth.callback.Callback

class ViewOrderActivity : AppCompatActivity() {


//    private lateinit var adapter: MessageAdapter
//
//    private val pusherAppKey = "1c56870d28b8f02404ee"
//    private val pusherAppCluster = "mt1"
//
//    val messageList = findViewById<RecyclerView>(R.id.messageList)
//    val txtMessage = findViewById<EditText>(R.id.txtMessage)
//    val btnSend = findViewById<Button>(R.id.btnSend)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_order)


//        val messageList = findViewById<RecyclerView>(R.id.messageList)
//        val txtMessage = findViewById<EditText>(R.id.txtMessage)
//        val btnSend = findViewById<Button>(R.id.btnSend)
//        messageList.layoutManager = LinearLayoutManager(this)
//        adapter = MessageAdapter(this)
//        messageList.adapter = adapter
//
//
//        btnSend.setOnClickListener {
//            if(txtMessage.text.isNotEmpty()) {
//                val message = Message(
//                    App.user,
//                    txtMessage.text.toString(),
//                    Calendar.getInstance().timeInMillis
//                )
//
//                val call = ChatService.create().postMessage(message)
//
//                call.enqueue( object : retrofit2.Callback<Void> {
//                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                        resetInput()
//                        if (!response.isSuccessful) {
//                            Log.e("TAG", response.code().toString());
//                            Toast.makeText(applicationContext,"Response was not successful", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Void>, t: Throwable) {
//                        resetInput()
//                        Log.e("TAG", t.toString());
//                        Toast.makeText(applicationContext,"Error when calling the service", Toast.LENGTH_SHORT).show()
//                    }
//
//                })
//            } else {
//                Toast.makeText(applicationContext,"Message should not be empty", Toast.LENGTH_SHORT).show()
//            }
//        }
//        setupPusher()




    }
//    private fun resetInput() {
//        // Clean text box
//        txtMessage.text.clear()
//
//        // Hide keyboard
//        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
//    }
//
//    private fun setupPusher() {
//        val options = PusherOptions()
//        options.setCluster(pusherAppCluster)
//
//        val pusher = Pusher(pusherAppKey, options)
//        val channel = pusher.subscribe("chat")
//
//        channel.bind("new_message") { channelName, eventName, data ->
//            val jsonObject = JSONObject(data)
//
//            val message = Message(
//                jsonObject["user"].toString(),
//                jsonObject["message"].toString(),
//                jsonObject["time"].toString().toLong()
//            )
//
//            runOnUiThread {
//                adapter.addMessage(message)
//                // scroll the RecyclerView to the last added element
//                messageList.scrollToPosition(adapter.itemCount - 1);
//            }
//
//        }
//
//        pusher.connect()
//    }
}