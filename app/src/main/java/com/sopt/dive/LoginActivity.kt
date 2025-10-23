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
import androidx.compose.ui.res.stringResource
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
            userId = data?.getStringExtra(IntentKeys.KEY_USER_ID)?:""
            userPw = data?.getStringExtra(IntentKeys.KEY_USER_PW)?:""
            userNickname = data?.getStringExtra(IntentKeys.KEY_USER_NICKNAME)?:""
            userMbti = data?.getStringExtra(IntentKeys.KEY_USER_MBTI)?:""
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
                text = stringResource(R.string.title_login),
                modifier = Modifier,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(40.dp))
            CustomTextField(
                stringResource(R.string.fieldname_id),
                stringResource(R.string.placeholder_id),
                loginId,
                {loginId = it},
                ""
            )
            CustomTextField(
                stringResource(R.string.fieldname_pw),
                stringResource(R.string.placeholder_pw),
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
                        putExtra(IntentKeys.KEY_USER_ID, userId)
                        putExtra(IntentKeys.KEY_USER_PW, userPw)
                        putExtra(IntentKeys.KEY_USER_NICKNAME, userNickname)
                        putExtra(IntentKeys.KEY_USER_MBTI, userMbti)
                    }
                    context.startActivity(intent)
                    Toast.makeText(context, context.getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, context.getString(R.string.fail_incorrect_login), Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, context.getString(R.string.fail_blank_login), Toast.LENGTH_SHORT).show()
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
        LoginScreen(Modifier.padding(horizontal = 16.dp))
    }
}
