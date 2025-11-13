package com.sopt.dive.ui.screen.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
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

    val userInfo = sharedPref.getUserId()
    val userPathId = userInfo.userId
    viewModel.getUserData(userPathId)

    val userUName = myState?.data?.username ?: ""
    val userName = myState?.data?.name ?: ""
    val userEmail = myState?.data?.email ?: ""
    val userAge = myState?.data?.age ?: 0


    Column (
        modifier = Modifier
            .padding(innerPadding)
            .padding(20.dp)
    ){
        Row (
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
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
