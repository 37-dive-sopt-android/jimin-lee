package com.sopt.dive.ui.screen.my

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.data.UiState
import com.sopt.dive.data.local.SharedPreference
import com.sopt.dive.ui.screen.my.MyViewModel
import com.sopt.dive.ui.screen.my.component.Info

@Composable
fun MyScreen(
    viewModel: MyViewModel = viewModel(),
    innerPadding: PaddingValues
) {
    val myState by viewModel.myState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val sharedPref = SharedPreference(context)
    val userPathId by rememberSaveable { mutableStateOf(sharedPref.getUserId().userId) }

    LaunchedEffect(userPathId) {
        viewModel.getUserData(userPathId)
    }

    when (val state = myState) {
        is UiState.Loading -> {
            LoadingHomeScreen(innerPadding)
        }
        is UiState.Success -> {
            val user = state.data?.data
            SuccessHomeScreen(
                userUName = user?.username ?: "",
                userName = user?.name ?: "",
                userEmail = user?.email ?: "",
                userAge = user?.age ?: 0,
                innerPadding = innerPadding
            )
        }
        is UiState.Failure -> {
            Toast.makeText(context, "내 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
private fun LoadingHomeScreen(
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun SuccessHomeScreen (
    userName: String,
    userUName: String,
    userEmail: String,
    userAge: Int,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = stringResource(R.string.main_profileimg),
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(
                text = userName,
                modifier = Modifier.padding(start = 20.dp),
                fontSize = 20.sp
            )
        }
        Text(
            text = "안녕하세요 ${userName}입니다",
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 20.sp
        )

        Spacer(Modifier.height(20.dp))

        Info(
            infoName = stringResource(R.string.fieldname_id),
            infoContent = userUName
        )
        Info(
            infoName = stringResource(R.string.fieldname_email),
            infoContent = userEmail
        )
        Info(
            infoName = stringResource(R.string.fieldname_age),
            infoContent = userAge.toString()
        )
    }
}
