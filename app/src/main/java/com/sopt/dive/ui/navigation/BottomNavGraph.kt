package com.sopt.dive.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sopt.dive.ui.screen.HomeScreen
import com.sopt.dive.ui.screen.MyScreen
import com.sopt.dive.ui.screen.SearchScreen

@Composable
fun BottomNavGraph(
    paddingValues: PaddingValues,
    userId: String,
    userPw: String,
    userNickname: String,
    userMbti: String,
    navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItem.Home.route
    ) {
        composable(route = BottomBarItem.Home.route){
            HomeScreen(
                modifier = Modifier.padding(16.dp)
            )
        }
        composable(route = BottomBarItem.Search.route){
            SearchScreen()
        }
        composable(route = BottomBarItem.My.route){
            MyScreen(
                paddingValues = paddingValues,
                userId = userId,
                userPw = userPw,
                userNickname = userNickname,
                userMbti = userMbti,
                modifier = Modifier.Companion
                    .padding(paddingValues)
                    .padding(20.dp)
            )
        }
    }
}