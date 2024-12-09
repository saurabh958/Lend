package com.example.lend.onboarding.repository

import android.app.Activity
import android.util.Log
import com.example.lend.onboarding.viewmodel.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginRepoImplementation : LoginRepoInterface {


    override fun signUpNewUser(
        context: Activity,
        auth: FirebaseAuth,
        email: String,
        password: String,
        callBack: (Boolean, FirebaseUser?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    Log.d(MainActivityViewModel.TAG, "signUpNewUser: signed in  successfully ")
                    val user = auth.currentUser
                    Log.d(MainActivityViewModel.TAG, "signUpNewUser: user data is $user and email is ${user?.email}")
                    callBack(true, auth.currentUser)
                } else {
                    Log.e(MainActivityViewModel.TAG, "signUpNewUser: signup failed")
                    callBack(false, null)
                }
            }
    }
}