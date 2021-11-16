package com.example.writershome

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox

import android.widget.TextView

import android.widget.EditText
import android.widget.Toast

import com.example.writershome.R

import android.content.res.ColorStateList

import android.content.res.XmlResourceParser
import android.text.InputType
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.lang.Exception
import java.util.regex.Matcher
import java.util.regex.Pattern


class SignUp_Fragment : Fragment(), View.OnClickListener {
    private var fullName: EditText? = null
    private var emailId: EditText? = null
    private var termsbtn: TextView? = null
    private var mobileNumber: EditText? = null
    private var location: EditText? = null
    private var password: EditText? = null
    private var confirmPassword: EditText? = null
    private var login: TextView? = null
    private var signUpButton: Button? = null
    private var terms_conditions: CheckBox? = null
    private var show_hide_pssword: CheckBox? = null
    private val PASSWORD_PATTERN: Pattern =
        Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    ".{6,}" +
                    "$"
        )


    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.signup_layout, container, false)
        fullName = view.findViewById(R.id.fullName) as EditText
        termsbtn = view.findViewById(R.id.trms)
        emailId = view.findViewById(R.id.userEmailId) as EditText
        mobileNumber = view.findViewById(R.id.mobileNumber) as EditText
        location = view.findViewById(R.id.location) as EditText
        show_hide_pssword =view.findViewById(R.id.show_hide_pssword)
        password = view.findViewById(R.id.password) as EditText
        confirmPassword = view.findViewById(R.id.confirmPassword) as EditText
        signUpButton = view.findViewById(R.id.signUpBtn) as Button
        login = view.findViewById(R.id.already_user) as TextView
        terms_conditions = view.findViewById(R.id.terms_conditions) as CheckBox

        // Setting text selector over textviews
        val xrp = resources.getXml(R.drawable.text_selector)
        try {
            val csl = ColorStateList.createFromXml(
                resources,
                xrp
            )
            login!!.setTextColor(csl)
            terms_conditions!!.setTextColor(csl)
            termsbtn!!.setTextColor(csl)
        } catch (e: Exception) {
        }
        setListeners();
        termsbtn!!.setOnClickListener {

            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                ?.replace(
                    R.id.frameContainer, TermsFragment(),
                    Utils.TermsFragment
                )?.commit()
        }
        show_hide_pssword
            ?.setOnCheckedChangeListener { button, isChecked ->
                // If it is checkec then show password else hide
                // password
                if (isChecked) {
                    show_hide_pssword!!.setText(R.string.hide_pwd) // change
                    // checkbox
                    // text
                    password!!.inputType = InputType.TYPE_CLASS_TEXT
                    password!!.transformationMethod = HideReturnsTransformationMethod
                        .getInstance() // show password



                    confirmPassword!!.inputType = InputType.TYPE_CLASS_TEXT
                    confirmPassword!!.transformationMethod = HideReturnsTransformationMethod
                        .getInstance()
                } else {
                    show_hide_pssword!!.setText(R.string.show_pwd) // change
                    // checkbox
                    // text
                    password!!.inputType = (InputType.TYPE_CLASS_TEXT
                            or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    password!!.transformationMethod = PasswordTransformationMethod
                        .getInstance() // hide password

                    confirmPassword!!.inputType = (InputType.TYPE_CLASS_TEXT
                            or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    confirmPassword!!.transformationMethod = PasswordTransformationMethod
                        .getInstance() // hide password
                }
            }

        return view
    }





    // Set Listeners
    private fun setListeners() {
        signUpButton!!.setOnClickListener(this)
        login!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        // Get all edittext texts
        val getFullName = fullName!!.text.toString()
        val getEmailId = emailId!!.text.toString()
        val getMobileNumber = mobileNumber!!.text.toString()
        val getLocation = location!!.text.toString()
        val getPassword = password!!.text.toString()
        val getConfirmPassword = confirmPassword!!.text.toString()
        when (v.id) {
            R.id.signUpBtn -> {
                when {

                    (getFullName == "" || getFullName.length == 0 || getEmailId == "" || getEmailId.length == 0 || getMobileNumber == "" || getMobileNumber.length == 0 || getLocation == "" || getLocation.length == 0 || getPassword == "" || getPassword.length == 0 || getConfirmPassword == "" || getConfirmPassword.length == 0) -> {
                        Toast.makeText(context, "All fields are required", Toast.LENGTH_LONG).show()
                    }
                    (getConfirmPassword != getPassword) -> {
                        Toast.makeText(context, "Both password doesn't match", Toast.LENGTH_LONG)
                            .show()
                    }
                    (!terms_conditions!!.isChecked) -> {
                        Toast.makeText(
                            context,
                            "Please agree to the Terms and Conditions to continue.",
                            Toast.LENGTH_LONG
                        ).show()

                    }
                    !validateEmail() -> {
                    }
                    !validatePassword() -> {

                    }
                    else -> {
                        createAccount()
                    }

                }
            }
            R.id.already_user ->
                // Replace login fragment
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                    ?.replace(
                        R.id.frameContainer, Login_Fragment(),
                        Utils.Login_Fragment
                    )!!.commit()
        }
    }

    private fun createAccount() {
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Creating Account")
        progressDialog.setMessage("please wait...")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
        val mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(emailId!!.text.toString(), password!!.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    saveUserData()
                }
            }


    }
    private fun saveUserData() {
        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Creating Account")
        progressDialog.setMessage("please wait...")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()
        val userDbRef = FirebaseDatabase.getInstance().reference
            .child("Users")
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        val userDataHashMap = HashMap<String, Any>()



        userDataHashMap["fullname"] = fullName!!.text.toString().trim()
        userDataHashMap["location"] = location!!.text.toString().trim().toLowerCase()
        userDataHashMap["email"] = emailId!!.text.toString().trim()
        userDataHashMap["password"] = password!!.text.toString().trim()
        userDataHashMap["mobilenumber"] = mobileNumber!!.text.toString().trim()
        userDataHashMap["bio"] ="create bio"
        userDataHashMap["profilePicUrl"] = "https://firebasestorage.googleapis.com/v0/b/socialkt-c1888.appspot.com/o/default%2Fprofile.png?alt=media&token=f1fde46f-d68e-4fd1-8763-5ece1ac07444"

        userDbRef
            .child(userID)
            .updateChildren(userDataHashMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    progressDialog.dismiss()
                    Toast.makeText(
                        context,
                        "Account created successfully",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }
            }.addOnFailureListener { taskId ->
                Toast.makeText(
                    context,
                    taskId.message!!,
                    Toast.LENGTH_SHORT
                ).show()
            }

    }

    private fun validateEmail(): Boolean {
        return when {
            TextUtils.isEmpty(emailId!!.text.toString()) -> {
                emailId!!.setError("Field can't be empty")
                return false
            }
            !(Patterns.EMAIL_ADDRESS.matcher(emailId!!.text.toString()).matches()) -> {
                emailId!!.setError("Enter a valid Email Adress")
                return false
            }
            else -> {
                return true
            }
        }
    }

    private fun validatePassword(): Boolean {
        return when {
            TextUtils.isEmpty(password!!.text.toString()) -> {
                password!!.setError("Field can't be empty")
                return false
            }
            !(PASSWORD_PATTERN.matcher(password!!.text.toString()).matches()) -> {
                password!!.setError("Should contain atleast 1 uppercase,1 lowercase, 1 digit")
                return false
            }
            else -> {
                return true
            }
        }
    }


}