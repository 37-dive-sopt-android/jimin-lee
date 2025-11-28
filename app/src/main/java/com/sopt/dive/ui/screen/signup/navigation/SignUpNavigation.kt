package com.sopt.dive.ui.screen.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.sopt.dive.data.route.Login
import com.sopt.dive.data.route.SignUp
import com.sopt.dive.ui.screen.signup.SignUpRoute

fun NavGraphBuilder.signupNavGraph(
    navController: NavController,
    innerPadding: PaddingValues
) {
    composable<SignUp> {
        SignUpRoute(
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