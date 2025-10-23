package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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


@Composable
private fun SignUpScreen(
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

    val idError = if (userId.isNotEmpty() && userId.length !in 6..10) stringResource(R.string.error_message_id) else ""
    val pwError = if (userPw.isNotEmpty() && userPw.length !in 8..12) stringResource(R.string.error_message_pw) else ""
    val nickError = if (userNickname.isNotEmpty() && userNickname.isBlank()) stringResource(R.string.error_message_nickname) else ""
    val mbtiError = if (userMbti.isNotEmpty() && !userMbti.matches(Regex("^[a-zA-Z]{4}$"))) stringResource(R.string.error_message_mbti) else ""


    val errorCheck = userId.length in 6..10 &&
            userPw.length in 8..12 &&
            userNickname.isNotBlank() &&
            userMbti.matches(Regex("^[a-zA-Z]{4}$"))


    Column (
        modifier = Modifier.padding(top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title_signup),
            modifier = Modifier,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(40.dp))
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_id),
            placeholder = stringResource(R.string.placeholder_id),
            text = userId,
            onTextChange = { userId = it },
            isPassword = false,
            error = idError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_pw),
            placeholder = stringResource(R.string.placeholder_pw),
            text = userPw,
            onTextChange = { userPw = it },
            isPassword = true,
            error = pwError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_nickname),
            placeholder = stringResource(R.string.placeholder_nickname),
            text = userNickname,
            onTextChange = { userNickname = it },
            isPassword = false,
            error = nickError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_mbti),
            placeholder = stringResource(R.string.placeholder_mbti),
            text = userMbti,
            onTextChange = { userMbti = it },
            isPassword = false,
            error = mbtiError
        )

        Spacer(modifier.weight(1f))

        SignupBtn(
            containerColor = Color.Black,
            contentColor = Color.White,
            onClick = {
                if (errorCheck) {
                    onSignUpSuccess(userId, userPw, userNickname, userMbti)
                    Toast.makeText(
                        context,
                        context.getString(R.string.success_signup),
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.fail_signup),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    DiveTheme {
        SignUpScreen(Modifier.padding(horizontal = 16.dp), onSignUpSuccess = { _, _, _, _ -> })
    }
}
