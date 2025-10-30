package com.sopt.dive.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
import com.sopt.dive.navigation.BottomNavGraph

@Composable
fun MainScreen(
    userId: String,
    userPw: String,
    userNickname: String,
    userMbti: String,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Scaffold (
        bottomBar = { BottomBar(navController = navController) }
    ){
        Surface {
            Box(Modifier.padding(it)){
                BottomNavGraph(
                    userId = userId,
                    userPw = userPw,
                    userNickname = userNickname,
                    userMbti = userMbti,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf<BottomBarItem>(
        BottomBarItem.Home,
        BottomBarItem.Search,
        BottomBarItem.My
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

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
                AddItem(
                    item = screens,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    item: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    val destinationObject = when (item.route) {
        "home" -> Home
        "search" -> Search
        "my" -> My
        else -> Home
    }

    NavigationBarItem(
        selected = currentDestination?.route == destinationObject::class.qualifiedName,
        onClick = {
            navController.navigate(destinationObject) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
                restoreState = true
            }
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = item.route,
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

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen("d","d","d","d")
}
