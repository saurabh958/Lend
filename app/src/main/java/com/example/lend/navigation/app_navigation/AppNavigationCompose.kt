package com.example.lend.navigation.app_navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lend.MainActivity
import com.example.lend.dashboard.view.DashBoardCompose
import com.example.lend.onboarding.login.view.LoginComposable
import com.example.lend.onboarding.login.viewmodel.MainActivityViewModel
import com.example.lend.onboarding.signup.view.SignUpComposable
import com.example.lend.onboarding.signup.viewmodel.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth


@Composable
fun AppNavHost(
    context: MainActivity,
    modifier: Modifier = Modifier,
    auth: FirebaseAuth,
    startDestination: String = AppNavigation.Login.route,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppNavigation.Home.route) {
            //call dashboard compose
            DashBoardCompose(navController = navController)
        }

        composable(AppNavigation.Login.route) {
            val viewModel: MainActivityViewModel = hiltViewModel()
            LoginComposable(context = context, auth = auth, viewModel = viewModel, navController = navController)
        }

        composable(AppNavigation.Signup.route) {
            val viewModel: SignUpViewModel = hiltViewModel()
            SignUpComposable(
                context = context,
                auth = auth,
                viewModel = viewModel,
                navController = navController
            )
        }
    }
}