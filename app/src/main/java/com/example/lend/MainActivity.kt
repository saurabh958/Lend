package com.example.lend

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lend.composable.TextFieldComposable
import com.example.lend.ui.theme.LendTheme
import com.example.lend.viewmodel.MainActivityViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainActivityViewModel by viewModels()
        auth = Firebase.auth
        setContent {
            LendTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(this, auth, viewModel)
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        /*val currentUser = auth.currentUser
        if (currentUser != null) {
            //user is already logged in proceed with next step
        }*/
    }
}


@Composable
fun Greeting(
    context: MainActivity,
    auth: FirebaseAuth,
    viewModel: MainActivityViewModel,
    modifier: Modifier = Modifier
) {

    var isLoading by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .padding(top = 90.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = "Login",
            fontFamily = FontFamily.Default,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = modifier
                .padding(top = 80.dp)
        )
        TextFieldComposable(
            modifier = modifier,
            errorMessage = viewModel.emailError,
            isError = viewModel.emailError.isNotBlank(),
            value = viewModel.emailValue,
            placeholder = "Enter Your Mail id",
            onChange = { emailText ->
                viewModel.setEmail(emailText)
            }
        )
        Spacer(
            modifier = modifier
                .padding(top = 10.dp)
        )
        TextFieldComposable(
            modifier = modifier,
            errorMessage = viewModel.passError,
            isError = viewModel.passError.isNotBlank(),
            onChange = { passText ->
                viewModel.setPassword(passText)
            },
            value = viewModel.passValue,
            keyboardType = KeyboardType.Password,
            placeholder = "Enter Your Password"
        )
        Spacer(
            modifier = modifier
                .padding(top = 20.dp)
        )
        ElevatedButton(
            onClick = {
                isLoading = true
                if (viewModel.validateForm()) {
                    viewModel.signUpNewUser(
                        context = context,
                        auth = auth,
                        email = viewModel.emailValue,
                        password = viewModel.passValue
                    ) { isSignUpSuccess, userObject ->
                        isLoading = false
                        if (isSignUpSuccess) {
                            // go to next screen
                            Toast.makeText(context, "Sign Up Success", Toast.LENGTH_SHORT).show()
                        } else {
                            //handle failure cases
                            Toast.makeText(context, "Sign Up Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    strokeWidth = 2.dp,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(20.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else {
                Text(
                    text = "Login",
                )
            }
        }

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