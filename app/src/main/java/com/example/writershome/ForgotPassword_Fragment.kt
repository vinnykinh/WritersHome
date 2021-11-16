package com.example.writershome

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import android.widget.EditText
import android.widget.Toast

import com.example.writershome.R

import android.content.res.ColorStateList

import android.content.res.XmlResourceParser
import java.lang.Exception
import java.util.regex.Matcher
import java.util.regex.Pattern


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ForgotPassword_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ForgotPassword_Fragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var emailId: EditText? = null
    private var submit: TextView? = null
    private var back: TextView? = null

    fun ForgotPassword_Fragment() {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.forgotpassword_layout, container, false)

        emailId = view.findViewById(R.id.registered_emailid) as EditText
        submit = view.findViewById(R.id.forgot_button) as TextView
        back = view.findViewById(R.id.backToLoginBtn) as TextView

        // Setting text selector over textviews
        val xrp = resources.getXml(R.drawable.text_selector)
        try {
            val csl = ColorStateList.createFromXml(
                resources,
                xrp
            )
            back!!.setTextColor(csl)
            submit!!.setTextColor(csl)
        } catch (e: Exception) {
        }
        setListeners();


        return view
    }



    // Set Listeners over buttons
    private fun setListeners() {
        back!!.setOnClickListener(this)
        submit!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.backToLoginBtn ->
                // Replace Login Fragment on Back Presses
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                    ?.replace(
                        R.id.frameContainer, Login_Fragment(),
                        Utils.Login_Fragment
                    )!!.commit()
            R.id.forgot_button ->
                // Call Submit button task
                submitButtonTask()
        }
    }

    private fun submitButtonTask() {
        val getEmailId = emailId!!.text.toString()

        // Pattern for email id validation
        val p: Pattern = Pattern.compile(Utils.regEx)

        // Match the pattern
        val m: Matcher = p.matcher(getEmailId)

        // First check if email id is not null else show error toast
        if (getEmailId == "" || getEmailId.length == 0) CustomToast().Show_Toast(
            requireActivity(), requireView(),
            "Please enter your Email Id."
        ) else if (!m.find()) CustomToast().Show_Toast(
            requireActivity(), requireView(),
            "Your Email Id is Invalid."
        ) else Toast.makeText(
            activity, "Get Forgot Password.",
            Toast.LENGTH_SHORT
        ).show()
    }


}