package com.example.lend.onboarding.login.repository

import android.app.Activity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface LoginRepoInterface {
    fun signUpNewUser(
        context: Activity,
        auth: FirebaseAuth,
        email: String,
        password: String,
        callBack: (Boolean, FirebaseUser?) -> Unit
    )
}