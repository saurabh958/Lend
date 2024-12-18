package com.example.lend.onboarding.signup.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lend.MainActivity
import com.example.lend.navigation.app_navigation.AppNavigation
import com.example.lend.onboarding.composable.TextFieldComposable
import com.example.lend.onboarding.signup.viewmodel.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun SignUpComposable(
    context: MainActivity,
    auth: FirebaseAuth,
    viewModel: SignUpViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    var isLoading by remember { mutableStateOf(false) }

    MaterialTheme {
        Column(
            modifier = modifier
                .padding(top = 90.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "SignUp",
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
                                navController.navigate(AppNavigation.Home.route) {
                                    popUpTo(AppNavigation.Home.route)
                                }
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
                        text = "Sign Up",
                    )
                }
            }

            Spacer(modifier = modifier
                .padding(top = 20.dp))
            ElevatedButton(
                onClick = {
                    navController.navigate(AppNavigation.Login.route) {
                        popUpTo(AppNavigation.Login.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}