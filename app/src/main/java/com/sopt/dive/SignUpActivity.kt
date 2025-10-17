package com.sopt.dive

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
                Scaffold(
                    modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpScreen(
                        title = "SIGN UP",
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 16.dp),
                        onSignUpSuccess = { userId, userPw, userNickname, userMbti ->
                            val intent = Intent().apply {
                                putExtra("userId", userId)
                                putExtra("userPw", userPw)
                                putExtra("userNickname", userNickname)
                                putExtra("userMbti", userMbti)
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


@Composable
fun SignUpScreen(
    title: String,
    modifier: Modifier = Modifier,
    onSignUpSuccess: (
        id: String,
        pw: String,
        nickname: String,
        mbti: String ) -> Unit
) {

    var userId by rememberSaveable { mutableStateOf("") }
    var userPw by rememberSaveable { mutableStateOf("") }
    var userNickname by rememberSaveable { mutableStateOf("") }
    var userMbti by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    var idError = if (userId.isNotEmpty() && userId.length !in 6..10) "아이디는 6~10글자로 입력해주세요" else ""
    var pwError = if (userPw.isNotEmpty() && userPw.length !in 8..12) "비밀번호는 8~12글자로 입력해주세요" else ""
    var nickError = if (userNickname.isNotEmpty() && userNickname.isBlank()) "닉네임은 1글자 이상 입력해주세요 (공백만 입력 불가)" else ""
    var mbtiError = if (userMbti.isNotEmpty() && !userMbti.matches(Regex("^[a-zA-Z]{4}$"))) "MBTI는 4글자 영어로 입력해주세요" else ""


    val errorCheck = userId.length in 6..10 &&
            userPw.length in 8..12 &&
            userNickname.isNotBlank() &&
            userMbti.matches(Regex("^[a-zA-Z]{4}$"))

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
            CustomTextField(
                "ID",
                "아이디를",
                userId,
                { userId = it },
                idError
            )
            CustomTextField(
                "PW",
                "비밀번호를",
                userPw,
                { userPw = it },
                pwError
            )
            CustomTextField(
                "NICKNAME",
                "닉네임을",
                userNickname,
                { userNickname = it },
                nickError
            )
            CustomTextField(
                "MBTI",
                "MBTI를",
                userMbti,
                { userMbti = it },
                mbtiError
            )
        }
        Column (
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            SignupBtn(
                Color.Black,
                Color.White,
                {
                    if (errorCheck) {
                        onSignUpSuccess(userId, userPw, userNickname, userMbti)
                        Toast.makeText(context, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(context, "올바른 회원 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen("SIGN UP", Modifier.padding(horizontal = 16.dp), onSignUpSuccess = { _, _, _, _ -> })
    }
}
