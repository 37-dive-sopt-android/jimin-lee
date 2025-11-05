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
import com.sopt.dive.ui.screen.SignUpScreen

fun NavGraphBuilder.signupNavGraph(
    navController: NavController,
    innerPadding: PaddingValues
) {
    composable<SignUp> {
        SignUpScreen(
            navigateToLogin = {
                navController.navigate(
                    route = Login,
                    navOptions = navOptions {
                        popUpTo<SignUp> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                )
            },
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 16.dp)
        )
    }
}