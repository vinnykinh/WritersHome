package com.example.writershome

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.example.writershome.R
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.*
import androidx.fragment.app.FragmentManager
import android.content.res.ColorStateList

import android.content.res.XmlResourceParser
import android.text.InputType
import android.text.TextUtils
import android.view.animation.AnimationUtils

import android.widget.LinearLayout

import android.widget.CheckBox

import android.widget.TextView

import android.widget.EditText
import java.lang.Exception
import android.text.method.PasswordTransformationMethod

import android.text.method.HideReturnsTransformationMethod
import android.util.Log
import android.util.Patterns

import android.widget.CompoundButton

import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Matcher
import java.util.regex.Pattern
import android.view.View as View1

class Login_Fragment : Fragment(), View1.OnClickListener {

    private var param1: String? = null
    private var param2: String? = null
    private val PASSWORD_PATTERN: Pattern =
        Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    ".{6,}" +
                    "$"
        )
    private var emailid: EditText? = null
    private var password: EditText? = null
    private var loginButton: Button? = null
    private var googleloginButton: SignInButton? = null
    private var forgotPassword: TextView? = null
    private var signUp: TextView? = null
    private var show_hide_password: CheckBox? = null
    private var rememberme_btn: CheckBox? = null
    private var loginLayout: LinearLayout? = null
    private var shakeAnimation: Animation? = null
    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View1? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_layout, container, false)

        val fragmentManager = activity?.supportFragmentManager
        emailid = view.findViewById<EditText>(R.id.login_emailid)
        password = view.findViewById<EditText>(R.id.login_password)
        googleloginButton = view.findViewById(R.id.googlesigninbtn)
        loginButton = view.findViewById<Button>(R.id.loginBtn)
        forgotPassword = view.findViewById<TextView>(R.id.forgot_password)
        signUp = view.findViewById<Button>(R.id.createAccount)
        rememberme_btn = view.findViewById(R.id.remenberme_btn)
        show_hide_password = view
            .findViewById<CheckBox>(R.id.show_hide_password)
        loginLayout = view.findViewById<LinearLayout>(R.id.login_layout)

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(
            activity,
            R.anim.shake
        )
        googleloginButton!!.setOnClickListener {
            signIn()

        }
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("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")
            .requestEmail()
            .build()
        // Setting text selector over textviews
        val xrp = resources.getXml(R.drawable.text_selector)
        try {
            val csl = ColorStateList.createFromXml(
                resources,
                xrp
            )
            forgotPassword!!.setTextColor(csl)
            show_hide_password!!.setTextColor(csl)
            signUp!!.setTextColor(csl)
        } catch (e: Exception) {
        }

        rememberme_btn!!.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {  }
            }
        })
        loginButton?.setOnClickListener {
            when {
                !validateEmail() -> {
                }
                !validatePassword() -> {
                }
                else -> {
                    loginAccount()
                }

            }
        }
        forgotPassword!!.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                ?.replace(
                    R.id.frameContainer,
                    ForgotPassword_Fragment(),
                    Utils.ForgotPassword_Fragment
                )!!.commit()
        }
        setListeners();
        return view;
    }
    // Set Listeners
    private fun setListeners() {
        signUp?.setOnClickListener(this)

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
            ?.setOnCheckedChangeListener { button, isChecked ->
                // If it is checkec then show password else hide
                // password
                if (isChecked) {
                    show_hide_password!!.setText(R.string.hide_pwd) // change
                    // checkbox
                    // text
                    password!!.inputType = InputType.TYPE_CLASS_TEXT
                    password!!.transformationMethod = HideReturnsTransformationMethod
                        .getInstance() // show password
                } else {
                    show_hide_password!!.setText(R.string.show_pwd) // change
                    // checkbox
                    // text
                    password!!.inputType = (InputType.TYPE_CLASS_TEXT
                            or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                    password!!.transformationMethod = PasswordTransformationMethod
                        .getInstance() // hide password
                }
            }
    }
    override fun onClick(v: View1?) {
        when (v!!.id) {
            R.id.createAccount ->
                // Replace signup frgament with animation
                fragmentManager
                    ?.beginTransaction()
                    ?.setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                    ?.replace(
                        R.id.frameContainer, SignUp_Fragment(),
                        Utils.SignUp_Fragment
                    )?.commit()
        }
    }
    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("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")
            .requestEmail()
            .build()
        val signInIntent = GoogleSignIn.getClient(context, gso).signInIntent
        startActivityForResult(signInIntent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 101) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val auth = Firebase.auth
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = auth.currentUser
                    Toast.makeText(context,"Successfully logged in", Toast.LENGTH_LONG).show()
                   startActivity(Intent(context, MainActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)

                }
            }
    }

    private fun loginAccount() {
        val email = emailid!!.text.toString().trim()
        val password = password!!.text.toString().trim()

        val progressDialog = ProgressDialog(context)
        progressDialog.setTitle("Logging in Account")
        progressDialog.setMessage("please wait...")
        progressDialog.setCanceledOnTouchOutside(false)
        progressDialog.show()

        val mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    progressDialog.dismiss()
                    Toast.makeText(
                        context,
                        "Account logged in successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }

            }.addOnFailureListener { taskId ->
                progressDialog.dismiss()
                Toast.makeText(context, taskId.message!!, Toast.LENGTH_SHORT).show()
            }
    }
    private fun validateEmail(): Boolean {
        return when {
            TextUtils.isEmpty(emailid!!.text.toString()) -> {
                emailid!!.setError("Field can't be empty")
                false
            }
            !(Patterns.EMAIL_ADDRESS.matcher(emailid!!.text.toString()).matches()) -> {
                emailid!!.setError("Enter a valid Email Adress")
                false
            }
            else -> {
                true
            }
        }
    }

    private fun validatePassword(): Boolean {
        return when {
            TextUtils.isEmpty(password!!.text.toString()) -> {
                password!!.setError("Field can't be empty")
                false
            }
            !(PASSWORD_PATTERN.matcher(password!!.text.toString()).matches()) -> {
                password!!.setError("Should contain atleast 1 uppercase,1 lowercase, 1 digit")
                false
            }
            else -> {
                true
            }
        }
    }
//     override fun onStart() {
//        super.onStart()
//        if (FirebaseAuth.getInstance().currentUser!=null)
//        {
//            Toast.makeText(
//                context,
//                "Account logged in successfully",
//                Toast.LENGTH_SHORT
//            ).show()
//            val intent = Intent(context, MainActivity::class.java)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//            startActivity(intent)
//        }
//
//    }


}

