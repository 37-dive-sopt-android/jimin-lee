package com.sopt.dive.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.data.route.Home
import com.sopt.dive.data.route.My
import com.sopt.dive.data.route.Search
import com.sopt.dive.ui.screen.HomeScreen
import com.sopt.dive.ui.screen.MyScreen
import com.sopt.dive.ui.screen.SearchScreen

@Composable
fun BottomNavGraph(
    userId: String,
    userPw: String,
    userNickname: String,
    userMbti: String,
    navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItem.Home.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable<Home>{
            HomeScreen()
        }
        composable<Search>{
            SearchScreen()
        }
        composable<My>{
            MyScreen(
                userId = userId,
                userPw = userPw,
                userNickname = userNickname,
                userMbti = userMbti
            )
        }
    }
}