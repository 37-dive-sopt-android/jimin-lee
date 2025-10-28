package com.sopt.dive.screen

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.component.CustomButton
import com.sopt.dive.component.CustomTextField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.utils.IntentKeys
import kotlinx.serialization.Serializable

@Serializable
data object Login

@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
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
                fieldName = stringResource(R.string.fieldname_id),
                placeholder = stringResource(R.string.placeholder_id),
                text = loginId,
                onTextChange = {loginId = it},
                isPassword = false,
                error = ""
            )
            CustomTextField(
                fieldName = stringResource(R.string.fieldname_pw),
                placeholder = stringResource(R.string.placeholder_pw),
                text = loginPw,
                onTextChange = {loginPw = it},
                isPassword = true,
                error = ""
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
                        val intent = Intent(context, MainActivity::class.java).apply {
                            putExtra(IntentKeys.KEY_USER_ID, userId)
                            putExtra(IntentKeys.KEY_USER_PW, userPw)
                            putExtra(IntentKeys.KEY_USER_NICKNAME, userNickname)
                            putExtra(IntentKeys.KEY_USER_MBTI, userMbti)
                        }
                        //navigateToMain
                        context.startActivity(intent)
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
                /*val intent = Intent(context, SignUpActivity::class.java)
                launcher.launch(intent)*/
                navigateToSignUp()
            }
        )
    }
}

/*@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    DiveTheme {
        LoginScreen(Modifier.padding(horizontal = 16.dp))
    }
}*/
