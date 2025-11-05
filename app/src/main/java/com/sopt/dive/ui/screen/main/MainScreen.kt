package com.sopt.dive.ui.screen.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.navigation.BottomBar
import com.sopt.dive.navigation.MainNavHost

@Composable
fun MainScreen(
) {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }
    ){ innerPadding ->
        MainNavHost(
            navController = navController,
            innerPadding = innerPadding
        )
    }
}
