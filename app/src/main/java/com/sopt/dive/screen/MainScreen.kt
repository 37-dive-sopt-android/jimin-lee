package com.sopt.dive.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.navigation.BottomBarItem
import com.sopt.dive.ui.navigation.BottomNavGraph
import kotlinx.serialization.Serializable

@Serializable
data class Main(
    val id: String,
    val pw: String,
    val nickname: String,
    val mbti: String
)

@Composable
fun MainScreen(
    paddingValues: PaddingValues,
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
                    paddingValues = paddingValues,
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

    NavigationBar (
        containerColor = Color.Gray,

    ) {
        screens.forEach { screens ->
            AddItem(item = screens, currentDestination = currentDestination, navController =navController )
        }
    }
}


@Composable
fun RowScope.AddItem(
    item: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    NavigationBarItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == item.route
        } == true,
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
        icon = {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = item.route,
                modifier = Modifier.size(30.dp)
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
    MainScreen(PaddingValues(),"d","d","d","d", Modifier.padding(20.dp))
}
