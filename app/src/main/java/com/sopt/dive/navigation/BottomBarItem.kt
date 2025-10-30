package com.sopt.dive.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItem(
    val route: String,
    val icon: ImageVector
){
    data object Home: BottomBarItem(
        route = "home",
        icon = Icons.Filled.Home
    )
    data object Search: BottomBarItem(
        route = "search",
        icon = Icons.Filled.Search
    )
    data object My: BottomBarItem(
        route = "my",
        icon = Icons.Filled.Person
    )
}