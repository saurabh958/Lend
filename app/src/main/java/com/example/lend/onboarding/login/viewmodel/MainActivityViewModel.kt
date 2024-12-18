package com.example.lend.onboarding.login.viewmodel

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lend.onboarding.login.repository.LoginRepoImplementation
import com.example.lend.onboarding.login.repository.LoginRepoInterface
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: LoginRepoInterface) : ViewModel() {


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
        viewModelScope.launch {
            repository.signUpNewUser(context, auth, email, password, callBack)
        }
    }

    companion object {
        const val TAG = "TEST!"
    }
}