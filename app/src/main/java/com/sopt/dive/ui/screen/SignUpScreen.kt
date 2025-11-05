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
import com.sopt.dive.data.local.SharedPreference
import com.sopt.dive.ui.component.CustomButton
import com.sopt.dive.ui.component.CustomTextField

private val MBTI_PATTERN = Regex("^[a-zA-Z]{4}$")

@Composable
fun SignUpScreen(
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val prefs = SharedPreference(context)

    var userId by rememberSaveable { mutableStateOf("") }
    var userPw by rememberSaveable { mutableStateOf("") }
    var userNickname by rememberSaveable { mutableStateOf("") }
    var userMbti by rememberSaveable { mutableStateOf("") }

    val isIdValid = userId.length in 6..10
    val isPwValid = userPw.length in 8..12
    val isNickValid = userNickname.isNotBlank()
    val isMbtiValid = userMbti.matches(MBTI_PATTERN)

    val idError = if (userId.isNotEmpty() && !isIdValid) stringResource(R.string.error_message_id) else ""
    val pwError = if (userPw.isNotEmpty() && !isPwValid) stringResource(R.string.error_message_pw) else ""
    val nickError = if (userNickname.isNotEmpty() && !isNickValid) stringResource(R.string.error_message_nickname) else ""
    val mbtiError = if (userMbti.isNotEmpty() && !isMbtiValid) stringResource(R.string.error_message_mbti) else ""

    val isSignUpValid = isIdValid && isPwValid && isNickValid && isMbtiValid

    Column (
        modifier = modifier.padding(top = 20.dp),
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
            error = nickError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_mbti),
            placeholder = stringResource(R.string.placeholder_mbti),
            text = userMbti,
            onTextChange = { userMbti = it },
            error = mbtiError
        )

        Spacer(modifier.weight(1f))

        CustomButton(
            buttonName = stringResource(R.string.button_signup),
            containerColor = Color.Black,
            contentColor = Color.White,
            onClick = {
                if (isSignUpValid) {
                    prefs.saveUserInfo(userId, userPw, userNickname, userMbti)
                    navigateToLogin()
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
