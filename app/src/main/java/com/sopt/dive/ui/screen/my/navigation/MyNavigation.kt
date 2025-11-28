package com.sopt.dive.ui.screen.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sopt.dive.data.route.My
import com.sopt.dive.ui.screen.my.MyRoute

fun NavGraphBuilder.myNavGraph(
    innerPadding: PaddingValues
) {
    composable<My>{
        MyRoute(innerPadding = innerPadding)
    }
}