package com.sopt.dive.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun Info (
    infoName: String,
    infoContent: String,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ){
        Text(
            text = infoName,
            modifier = Modifier.padding(bottom = 5.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = infoContent,
            color = Color.Gray,
            fontSize = 20.sp,

        )
    }
}



@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    DiveTheme {
        Info("ID", "vavava")
    }
}

