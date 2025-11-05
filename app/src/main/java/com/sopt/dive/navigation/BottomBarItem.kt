package com.sopt.dive.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.sopt.dive.data.route.Home
import com.sopt.dive.data.route.MainTabRoute
import com.sopt.dive.data.route.My
import com.sopt.dive.data.route.Search

enum class BottomBarItem(
    val route: MainTabRoute,
    val icon: ImageVector
){
    HOME(
        route = Home,
        icon = Icons.Filled.Home
    ),
    SEARCH(
        route = Search,
        icon = Icons.Filled.Search
    ),
    MY(
        route = My,
        icon = Icons.Filled.Person
    );
}