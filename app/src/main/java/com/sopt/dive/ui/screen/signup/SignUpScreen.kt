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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.data.dto.request.signup.RequestSignUpDto
import com.sopt.dive.data.local.SharedPreference
import com.sopt.dive.ui.component.CustomButton
import com.sopt.dive.ui.component.CustomTextField
import retrofit2.Response.success

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel(),
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {

    val signupState = viewModel.signupState.collectAsStateWithLifecycle()
    var isSignupSuccess = signupState.value

    val context = LocalContext.current

    var userId by rememberSaveable { mutableStateOf("") }
    var userPw by rememberSaveable { mutableStateOf("") }
    var userNickname by rememberSaveable { mutableStateOf("") }
    var userEmail by rememberSaveable { mutableStateOf("") }
    var userAge by rememberSaveable { mutableStateOf("") }

    val idError = if (userId.isNotBlank()) SignUpValidator.validateId(userId) ?: "" else ""
    val pwError = if (userPw.isNotBlank()) SignUpValidator.validatePw(userPw) ?: "" else ""
    val nickError = if (userNickname.isNotBlank()) SignUpValidator.validateNickname(userNickname) ?: "" else ""
    val emailError = if (userEmail.isNotBlank()) SignUpValidator.validateEmail(userEmail) ?: "" else ""
    val ageError = if (userAge.isNotBlank()) SignUpValidator.validateAge(userAge.toIntOrNull() ?: 0) ?: "" else ""

    val isSignUpValid = SignUpValidator.validateAll(userId,userPw,userNickname,userEmail,userAge.toIntOrNull() ?: 0)

    val userInfo = RequestSignUpDto(userId, userPw, userNickname, userEmail, userAge.toIntOrNull() ?: 0)

    isSignupSuccess?.let { state ->
        if (state.success) {
            Toast.makeText(context, R.string.success_signup, Toast.LENGTH_SHORT).show()
            navigateToLogin()
        } else {
            Toast.makeText(context, R.string.fail_existed_username, Toast.LENGTH_SHORT).show()
        }
        viewModel.resetSignUp()
    }

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
            fieldName = stringResource(R.string.fieldname_email),
            placeholder = stringResource(R.string.placeholder_email),
            text = userEmail,
            onTextChange = { userEmail = it },
            error = emailError
        )
        CustomTextField(
            fieldName = stringResource(R.string.fieldname_age),
            placeholder = stringResource(R.string.placeholder_age),
            text = userAge,
            onTextChange = { userAge = it },
            error = ageError
        )

        Spacer(modifier.weight(1f))

        CustomButton(
            buttonName = stringResource(R.string.button_signup),
            containerColor = Color.Black,
            contentColor = Color.White,
            onClick = {
                if(isSignUpValid) {
                    viewModel.postSignUp(userInfo)
                } else {
                    Toast.makeText(context,R.string.fail_signup,Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}
