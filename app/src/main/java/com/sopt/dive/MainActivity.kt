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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.component.Info
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
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
fun MainScreen(modifier: Modifier = Modifier) {
    Column (modifier){
        Row (
            modifier = Modifier.padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "profileImg",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "이지민",
                modifier = modifier,
                fontSize = 20.sp
            )
        }
        Text(
            text = "안녕하세요 이지민입니다",
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 20.sp
        )

        Spacer(Modifier.height(40.dp))

        Info("ID", "안녕하세요")
        Info("PW", "1234")
        Info("NICKNAME", "지민")
        Info("MBTI", "AAAA")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    DiveTheme {
        MainScreen(Modifier.padding(horizontal = 20.dp))
    }
}