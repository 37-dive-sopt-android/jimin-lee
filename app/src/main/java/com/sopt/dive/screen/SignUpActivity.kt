package com.sopt.dive.screen

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
import com.sopt.dive.R
import com.sopt.dive.component.CustomButton
import com.sopt.dive.component.CustomTextField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.utils.IntentKeys

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                /*Scaffold(
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
                }*/
            }
        }
    }
}
