package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.sopt.dive.data.route.Home
import com.sopt.dive.data.route.Login
import com.sopt.dive.data.route.SignUp
import com.sopt.dive.ui.screen.LoginScreen


fun NavGraphBuilder.loginNavGraph(
    navController: NavController,
    innerPadding: PaddingValues
){
    composable<Login> {
        LoginScreen(
            navigateToMain = {
                navController.navigate(
                    route = Home,
                    navOptions = navOptions {
                        popUpTo<Login> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
            },
            navigateToSignUp = {
                navController.navigate(route = SignUp)
            },
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 16.dp)
        )
    }
}