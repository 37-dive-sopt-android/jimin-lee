package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sopt.dive.data.route.Login

@Composable
fun MainNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        loginNavGraph(
            navController = navController,
            innerPadding = innerPadding
        )

        signupNavGraph(
            navController = navController,
            innerPadding = innerPadding
        )

        homeNavGraph(
            innerPadding = innerPadding
        )

        searchNavGraph(
            innerPadding = innerPadding
        )

        myNavGraph(
            innerPadding = innerPadding
        )
    }
}