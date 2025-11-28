package com.sopt.dive.ui.screen.login

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.ui.common.UiState
import com.sopt.dive.ui.component.CustomButton
import com.sopt.dive.ui.component.CustomTextField
import com.sopt.dive.data.local.SharedPreference

@Composable
fun LoginRoute(
    navigateToMain: () -> Unit,
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
) {
    val context = LocalContext.current

    val loginState by viewModel.loginState.collectAsStateWithLifecycle()
    val loginInfoState by viewModel.loginInfo.collectAsStateWithLifecycle()

    when (val state = loginState) {
        is UiState.Success -> {
            LaunchedEffect(state) {
                val response = state.data
                response?.let { userId ->
                    SharedPreference(context).saveUserId(response.userId)
                    Toast.makeText(context, R.string.success_login, Toast.LENGTH_SHORT).show()
                    navigateToMain()
                }
            }
        }

        is UiState.Failure -> {
            Toast.makeText(context, R.string.fail_incorrect_login, Toast.LENGTH_SHORT).show()
        }

        is UiState.Loading -> {

        }
    }

    LoginScreen(
        loginId = loginInfoState.loginId,
        loginPw = loginInfoState.loginPw,
        onLoginIdChange = viewModel::updateLoginId,
        onLoginPwChange = viewModel::updateLoginPw,
        onLoginClick = viewModel::postLogin,
        navigateToSignUp = navigateToSignUp,
        modifier = modifier

    )
}

@Composable
fun LoginScreen(
    loginId: String,
    loginPw: String,
    onLoginIdChange: (String) -> Unit,
    onLoginPwChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    navigateToSignUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

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
                onTextChange = onLoginIdChange
            )
            CustomTextField(
                fieldName = stringResource(R.string.fieldname_pw),
                placeholder = stringResource(R.string.placeholder_pw),
                text = loginPw,
                onTextChange = onLoginPwChange,
                isPassword = true
            )
        }

        Spacer(Modifier.weight(1f))

        CustomButton(
            buttonName = stringResource(R.string.button_login),
            containerColor = Color.Black,
            contentColor = Color.White,
            onClick = {
                if (loginId.isBlank() || loginPw.isBlank()) {
                    Toast.makeText(context, context.getString(R.string.fail_blank_login), Toast.LENGTH_SHORT).show()
                } else onLoginClick()
            }
        )
        CustomButton (
            buttonName = stringResource(R.string.button_signup),
            containerColor = Color.Transparent,
            contentColor = Color.Gray,
            onClick = navigateToSignUp
        )
    }
}
