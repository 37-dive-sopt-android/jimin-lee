package com.sopt.dive.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sopt.dive.data.route.Login
import com.sopt.dive.ui.screen.LoginScreen
import com.sopt.dive.data.route.Main
import com.sopt.dive.ui.screen.MainScreen
import com.sopt.dive.data.route.SignUp
import com.sopt.dive.ui.screen.SignUpScreen

@Composable
fun LoginNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Login("", "", "", "")
    ) {
        composable<Main> { backStackEntry ->
            val item = backStackEntry.toRoute<Main>()
            MainScreen(
                userId = item.id,
                userPw = item.pw,
                userNickname = item.nickname,
                userMbti = item.mbti,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
            )
        }

        composable<Login> { backStackEntry ->
            val item = backStackEntry.toRoute<Login>()
            LoginScreen(
                userId = item.id,
                userPw = item.pw,
                userNickname = item.nickname,
                userMbti = item.mbti,
                navigateToMain = { id, pw, nickname, mbti ->
                    navController.navigate(Main(id, pw, nickname, mbti))
                },
                navigateToSignUp = {
                    navController.navigate(SignUp)
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(all = 16.dp)
            )
        }

        composable<SignUp> {
            SignUpScreen(
                navigateToLogin = { id, pw, nickname, mbti ->
                    navController.navigate(Login(id, pw, nickname, mbti))
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(all = 16.dp)
            )
        }
    }
}