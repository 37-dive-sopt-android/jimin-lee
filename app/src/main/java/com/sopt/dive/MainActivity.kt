package com.sopt.dive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.component.Info
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra(IntentKeys.KEY_USER_ID)?:""
        val userPw = intent.getStringExtra(IntentKeys.KEY_USER_PW)?:""
        val userNickname = intent.getStringExtra(IntentKeys.KEY_USER_NICKNAME)?:""
        val userMbti = intent.getStringExtra(IntentKeys.KEY_USER_MBTI)?:""

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        userId = userId,
                        userPw = userPw,
                        userNickname = userNickname,
                        userMbti = userMbti,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(20.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MainScreen(
    userId: String,
    userPw: String,
    userNickname: String,
    userMbti: String,
    modifier: Modifier = Modifier
) {

    Column (modifier){
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
                text = userNickname,
                modifier = modifier,
                fontSize = 20.sp
            )
        }
        Text(
            text = "안녕하세요 ${userNickname}입니다",
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 20.sp
        )

        Spacer(Modifier.height(20.dp))

        Info(
            infoName = stringResource(R.string.fieldname_id),
            infoContent = userId
        )
        Info(
            infoName = stringResource(R.string.fieldname_pw),
            infoContent = userPw
        )
        Info(
            infoName = stringResource(R.string.fieldname_nickname),
            infoContent = userNickname
        )
        Info(
            infoName = stringResource(R.string.fieldname_mbti),
            infoContent = userMbti
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    DiveTheme {
        MainScreen("","","","", Modifier.padding(20.dp))
    }
}
