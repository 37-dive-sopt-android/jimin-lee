package com.sopt.dive.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.utils.IntentKeys

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra(IntentKeys.KEY_USER_ID)?:""
        val userPw = intent.getStringExtra(IntentKeys.KEY_USER_PW)?:""
        val userNickname = intent.getStringExtra(IntentKeys.KEY_USER_NICKNAME)?:""
        val userMbti = intent.getStringExtra(IntentKeys.KEY_USER_MBTI)?:""

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Login
                    ) {
                        composable<Main> {
                            MainScreen(
                                paddingValues = innerPadding,
                                navigateToLogin = {
                                    navController.navigate(Login)
                                },
                                userId = userId,
                                userPw = userPw,
                                userNickname = userNickname,
                                userMbti = userMbti,
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .padding(20.dp)
                            )
                        }

                        composable<Login> { backStackEntry ->
                            LoginScreen(
                                paddingValues = innerPadding,
                                navigateToMain = {
                                    navController.navigate(Main)
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
                                paddingValues = innerPadding,
                                navigateToLogin = {
                                    navController.navigate(Login)
                                },
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .padding(all = 16.dp),
                                onSignUpSuccess = { userId, userPw, userNickname, userMbti ->
                                    val intent = Intent().apply {
                                        putExtra(IntentKeys.KEY_USER_ID, userId)
                                        putExtra(IntentKeys.KEY_USER_PW, userPw)
                                        putExtra(IntentKeys.KEY_USER_NICKNAME, userNickname)
                                        putExtra(IntentKeys.KEY_USER_MBTI, userMbti)
                                    }
                                    setResult(RESULT_OK, intent)
                                    finish()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
