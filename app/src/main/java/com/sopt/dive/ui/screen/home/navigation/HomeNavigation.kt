package com.sopt.dive.ui.screen.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.dive.data.route.Home
import com.sopt.dive.ui.screen.home.HomeScreen

fun NavGraphBuilder.homeNavGraph(
    innerPadding: PaddingValues
) {
    composable<Home>{
        HomeScreen(innerPadding)
    }
}