package com.example.lend.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivityViewModel : ViewModel() {
    var emailValue by mutableStateOf("")
        private set
    var emailError by mutableStateOf("")
        private set

    var passValue by mutableStateOf("")
        private set
    var passError by mutableStateOf("")
        private set

    fun setEmail(email: String) {
        emailValue = email
    }

    fun setPassword(password: String) {
        passValue = password
    }

    private fun validateEmail(): Boolean {
        val email = emailValue.trim()
        var isValid = true
        var errorMessage = ""
        if (email.isBlank() || email.isEmpty()) {
            errorMessage = "Please fill email field"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Wrong email Format"
            isValid = false
        }
        emailError = errorMessage
        return isValid
    }


    private fun validatePassword(): Boolean {
        val password = passValue.trim()
        var isValid = true
        var errorMessage = ""

        if (password.isBlank() || password.isEmpty()) {
            errorMessage = "Please fill password field"
            isValid = false
        } else if (password.length < 6) {
            errorMessage = "Password must more than 6 character"
            isValid = false
        }
        passError = errorMessage
        return isValid
    }

    fun validateForm(): Boolean {
        return validateEmail() && validatePassword()
    }

    fun signUpNewUser(
        context: Activity,
        auth: FirebaseAuth,
        email: String,
        password: String,
        callBack: (Boolean, FirebaseUser?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signUpNewUser: signed in  successfully ")
                    val user = auth.currentUser
                    Log.d(TAG, "signUpNewUser: user data is $user and email is ${user?.email}")
                    callBack(true, auth.currentUser)
                } else {
                    Log.e(TAG, "signUpNewUser: signup failed")
                    callBack(false, null)
                }
            }
    }

    companion object {
        private const val TAG = "TEST!"
    }
}