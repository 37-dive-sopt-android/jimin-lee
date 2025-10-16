package com.sopt.dive

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.component.CustomTextField
import com.sopt.dive.component.SignupBtn
import com.sopt.dive.ui.theme.DiveTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(
                        title = "SIGN UP",
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(title: String, modifier: Modifier = Modifier) {

    var userId by rememberSaveable { mutableStateOf("") }
    var userPw by rememberSaveable { mutableStateOf("") }
    var userNickname by rememberSaveable { mutableStateOf("") }
    var userMbti by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    Column {
        Column (
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = modifier.padding(bottom = 5.dp),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            CustomTextField("ID", "아이디를", userId, { userId = it })
            CustomTextField("PW", "비밀번호를", userPw, { userPw = it })
            CustomTextField("NICKNAME", "닉네임을", userNickname, { userNickname = it })
            CustomTextField("MBTI", "MBTI를", userMbti, { userMbti = it })
        }
        Column (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ){
            SignupBtn(
                MaterialTheme.colorScheme.primary,
                Color.White,
                {
                    val intent = Intent().apply {
                        putExtra("userId",userId)
                        putExtra("userPw",userPw)
                        putExtra("userNickname",userNickname)
                        putExtra("userMbti",userMbti)
                    }
                    val activity = (context as Activity)
                    activity.setResult(Activity.RESULT_OK, intent)
                    activity.finish()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen("SIGN UP", Modifier.padding(horizontal = 16.dp))
    }
}