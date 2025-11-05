package com.sopt.dive.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.data.route.Home
import com.sopt.dive.data.route.My
import com.sopt.dive.data.route.Search
import com.sopt.dive.navigation.BottomBarItem
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

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf<BottomBarItem>(
        BottomBarItem.HOME,
        BottomBarItem.SEARCH,
        BottomBarItem.MY
    )
    val mainTabRoutes = listOf(
        Home::class.qualifiedName,
        Search::class.qualifiedName,
        My::class.qualifiedName
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute !in mainTabRoutes) {
        return
    }

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
    ){
        NavigationBar(
            modifier = Modifier.height(70.dp),
            containerColor = Color.White
        ) {
            screens.forEach { screens ->
                BottomBarItem(
                    item = screens,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun RowScope.BottomBarItem(
    item: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        selected = currentDestination?.route == item.route::class.qualifiedName,
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.Black,
            unselectedIconColor = Color.LightGray,
            indicatorColor = Color.Transparent
        )
    )
}
