package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sopt.dive.data.route.Login
import com.sopt.dive.ui.screen.home.navigation.homeNavGraph
import com.sopt.dive.ui.screen.login.navigation.loginNavGraph
import com.sopt.dive.ui.screen.my.navigation.myNavGraph
import com.sopt.dive.ui.screen.search.navigation.searchNavGraph
import com.sopt.dive.ui.screen.signup.navigation.signupNavGraph

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