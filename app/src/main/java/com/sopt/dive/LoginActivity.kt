package com.sopt.dive

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        title = "Welcome To SOPT",
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(all = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    title: String,
    modifier: Modifier = Modifier
) {

    var userId by rememberSaveable { mutableStateOf("") }
    var userPw by rememberSaveable { mutableStateOf("") }
    var userNickname by rememberSaveable { mutableStateOf("") }
    var userMbti by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            userId = data?.getStringExtra("userId")?:""
            userPw = data?.getStringExtra("userPw")?:""
            userNickname = data?.getStringExtra("userNickname")?:""
            userMbti = data?.getStringExtra("userMbti")?:""
        }
    }

    var loginId by rememberSaveable { mutableStateOf("") }
    var loginPw by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = modifier
    ){
        Column (
            modifier = Modifier.padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                modifier = Modifier,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(40.dp))
            CustomTextField(
                "ID",
                "아이디를",
                loginId,
                {loginId = it},
                ""
            )
            CustomTextField(
                "PW",
                "비밀번호를",
                loginPw,
                {loginPw = it},
                ""
            )
        }

        Spacer(Modifier.weight(1f))

        LoginBtn({
            if (loginId.isNotEmpty() && loginPw.isNotEmpty()) {
                if (loginId == userId && loginPw == userPw) {
                    val intent = Intent(context, MainActivity::class.java).apply {
                        putExtra("userId", userId)
                        putExtra("userPw", userPw)
                        putExtra("userNickname", userNickname)
                        putExtra("userMbti", userMbti)
                    }
                    context.startActivity(intent)
                    Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "아이디 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
            }
        })
        SignupBtn(
            Color.Transparent,
            Color.Gray,
            {
                val intent = Intent(context, SignUpActivity::class.java)
                launcher.launch(intent)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DiveTheme {
        LoginScreen("Welcome To SOPT", Modifier.padding(horizontal = 16.dp))
    }
}
