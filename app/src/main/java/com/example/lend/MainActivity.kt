package com.example.lend

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.lend.navigation.app_navigation.AppNavHost
import com.example.lend.ui.theme.LendTheme
import com.example.lend.onboarding.login.view.LoginComposable
import com.example.lend.onboarding.login.viewmodel.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        Log.d("TEST!", "onCreate: ")
        auth = Firebase.auth
        setContent {
            LendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(context = this, auth = auth)
                    //LoginComposable(this, auth)
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d("TEST!", "onStart: ")
        /*val currentUser = auth.currentUser
        if (currentUser != null) {
            //user is already logged in proceed with next step
        }*/
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LendTheme {
/*
        Greeting(viewModel = MainActivityViewModel())
*/
    }
}