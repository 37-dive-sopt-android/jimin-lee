package com.sopt.dive.ui.screen.signup

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

@Composable
fun SignUpRoute(
    navigateToLogin: () -> Unit,
    viewModel: SignUpViewModel = viewModel(),
    modifier: Modifier = Modifier,

) {
    val context = LocalContext.current

    val signupState by viewModel.signupState.collectAsStateWithLifecycle()
    val signupInfo by viewModel.signupInfo.collectAsStateWithLifecycle()

    when (val state = signupState) {
        is UiState.Success -> {
            LaunchedEffect(signupState) {
                val response = state.data
                response?.let {
                    Toast.makeText(context, R.string.success_signup, Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
            }
        }
        is UiState.Failure -> {
            Toast.makeText(context, R.string.fail_existed_username, Toast.LENGTH_SHORT).show()
        }
        is UiState.Loading -> {
        }
    }

    SignUpScreen(
        state = signupInfo,
        onUserUNameChange = viewModel::onUserUNameChange,
        onUserPwChange = viewModel::onUserPwChange,
        onUserNameChange = viewModel::onUserNameChange,
        onUserEmailChange = viewModel::onUserEmailChange,
        onUserAgeChange = viewModel::onUserAgeChange,
        onSignupClick = viewModel::postSignUp,
        modifier = modifier

    )
}

@Composable
fun SignUpScreen(
    state: SignUpUiState,
    onUserUNameChange: (String) -> Unit,
    onUserPwChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onUserEmailChange: (String) -> Unit,
    onUserAgeChange: (String) -> Unit,
    onSignupClick: () -> Unit,
    modifier: Modifier = Modifier,
) {


    val context = LocalContext.current

    /*LaunchedEffect(signupState) {
        when (val state = signupState) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                val response = state.data
                response?.let {
                    Toast.makeText(context, R.string.success_signup, Toast.LENGTH_SHORT).show()
                    navigateToLogin()
                }
            }
            is UiState.Failure -> {
                Toast.makeText(context, R.string.fail_existed_username, Toast.LENGTH_SHORT).show()
            }
        }
    }*/

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
            text = state.userUName,
            onTextChange = onUserUNameChange,
            error = state.idError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_pw),
            placeholder = stringResource(R.string.placeholder_pw),
            text = state.userPw,
            onTextChange = onUserPwChange,
            isPassword = true,
            error = state.pwError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_name),
            placeholder = stringResource(R.string.placeholder_name),
            text = state.userName,
            onTextChange = onUserNameChange,
            error = state.nickError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_email),
            placeholder = stringResource(R.string.placeholder_email),
            text = state.userEmail,
            onTextChange = onUserEmailChange,
            error = state.emailError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_age),
            placeholder = stringResource(R.string.placeholder_age),
            text = state.userAge,
            onTextChange = onUserAgeChange,
            error = state.ageError
        )

        Spacer(modifier.weight(1f))

        CustomButton(
            buttonName = stringResource(R.string.button_signup),
            containerColor = Color.Black,
            contentColor = Color.White,
            onClick = onSignupClick
        )
    }
}
