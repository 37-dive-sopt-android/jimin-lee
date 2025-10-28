package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sopt.dive.ui.screen.Login
import com.sopt.dive.ui.screen.LoginScreen
import com.sopt.dive.ui.screen.Main
import com.sopt.dive.ui.screen.MainScreen
import com.sopt.dive.ui.screen.SignUp
import com.sopt.dive.ui.screen.SignUpScreen
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Login("", "", "", "")
                    ) {
                        composable<Main> { backStackEntry ->
                            val item = backStackEntry.toRoute<Main>()
                            MainScreen(
                                paddingValues = innerPadding,
                                userId = item.id,
                                userPw = item.pw,
                                userNickname = item.nickname,
                                userMbti = item.mbti,
                                modifier = Modifier.Companion
                                    .padding(innerPadding)
                                    .padding(20.dp)
                            )
                        }

                        composable<Login> { backStackEntry ->
                            val item = backStackEntry.toRoute<Login>()
                            LoginScreen(
                                paddingValues = innerPadding,
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
                                modifier = Modifier.Companion
                                    .padding(innerPadding)
                                    .padding(all = 16.dp)
                            )
                        }

                        composable<SignUp> {
                            SignUpScreen(
                                paddingValues = innerPadding,
                                navigateToLogin = { id, pw, nickname, mbti ->
                                    navController.navigate(Login(id, pw, nickname, mbti))
                                },
                                modifier = Modifier.Companion
                                    .padding(innerPadding)
                                    .padding(all = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}