package com.sopt.dive.navigation

import com.sopt.dive.R

sealed class BottomBarItem(
    val route: String,
    val icon: Int
){
    data object Home: BottomBarItem(
        route = "home",
        icon = R.drawable.ic_home
    )
    data object Search: BottomBarItem(
        route = "search",
        icon = R.drawable.ic_search
    )
    data object My: BottomBarItem(
        route = "my",
        icon = R.drawable.ic_my
    )
}