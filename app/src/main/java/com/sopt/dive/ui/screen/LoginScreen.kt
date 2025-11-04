package com.sopt.dive.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.component.CustomButton
import com.sopt.dive.ui.component.CustomTextField

@Composable
fun LoginScreen(
    userId: String,
    userPw: String,
    userNickname: String,
    userMbti: String,
    navigateToMain: (id: String, pw: String, nickname: String, mbti: String) -> Unit,
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

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
                fieldName = stringResource(R.string.fieldname_id),
                placeholder = stringResource(R.string.placeholder_id),
                text = loginId,
                onTextChange = {loginId = it}
            )
            CustomTextField(
                fieldName = stringResource(R.string.fieldname_pw),
                placeholder = stringResource(R.string.placeholder_pw),
                text = loginPw,
                onTextChange = {loginPw = it},
                isPassword = true
            )
        }

        Spacer(Modifier.weight(1f))

        CustomButton(
            buttonName = stringResource(R.string.button_login),
            containerColor = Color.Black,
            contentColor = Color.White,
            onClick = {
                when {
                    loginId.isBlank() || loginPw.isBlank() -> {
                        Toast.makeText(context, context.getString(R.string.fail_blank_login), Toast.LENGTH_SHORT).show()
                    }
                    loginId == userId && loginPw == userPw -> {
                        navigateToMain(userId, userPw, userNickname, userMbti)
                        Toast.makeText(context, context.getString(R.string.success_login), Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(context, context.getString(R.string.fail_incorrect_login), Toast.LENGTH_SHORT).show()
                    }
                }
            })
        CustomButton (
            buttonName = stringResource(R.string.button_signup),
            containerColor = Color.Transparent,
            contentColor = Color.Gray,
            onClick = {
                navigateToSignUp()
            }
        )
    }
}
