package com.example.writershome


object Utils {
    //Email Validation pattern
    const val regEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\\\S+\$).{4,}\$"

    //Fragments Tags
    const val Login_Fragment = "Login_Fragment"
    const val SignUp_Fragment = "SignUp_Fragment"
    const val TermsFragment= "TermsFragment"
    const val ForgotPassword_Fragment = "ForgotPassword_Fragment"
}