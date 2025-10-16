package com.sopt.dive

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
import com.sopt.dive.component.LoginBtn
import com.sopt.dive.component.SignupBtn
import com.sopt.dive.ui.theme.DiveTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userId = intent.getStringExtra("userId")?:""
        val userPw = intent.getStringExtra("userPw")?:""
        val userNickname = intent.getStringExtra("userNickname")?:""
        val userMbti = intent.getStringExtra("userMbti")?:""

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        title = "Welcome To SOPT",
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                        userId = userId,
                        userPw = userPw,
                        userNickname = userNickname,
                        userMbti = userMbti
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    title: String,
    modifier: Modifier = Modifier,
    userId: String,
    userPw: String,
    userNickname: String,
    userMbti: String
) {

    val context = LocalContext.current

    val intent1 = Intent(context, MainActivity::class.java).apply {
        putExtra("userId",userId)
        putExtra("userPw",userPw)
        putExtra("userNickname",userNickname)
        putExtra("userMbti",userMbti)

        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }
    val intent2 = Intent(context, SignUpActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }

    var loginId by rememberSaveable { mutableStateOf("") }
    var loginPw by rememberSaveable { mutableStateOf("") }

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
            CustomTextField("ID", "아이디를", loginId, {loginId = it})
            CustomTextField("PW", "비밀번호를", loginPw, {loginPw = it})
        }
        Column (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ){
            LoginBtn({
                context.startActivity(intent1)
            })
            SignupBtn(
                Color.Transparent,
                Color.Gray,
                {
                    context.startActivity(intent2)
                }
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DiveTheme {
        LoginScreen("Welcome To SOPT", Modifier.padding(horizontal = 16.dp))
    }
}*/
