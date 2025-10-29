package com.sopt.dive.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Search",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 26.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "안녕하세요!!",
            modifier = Modifier
                .align(Alignment.Center),
            fontSize = 15.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreen(Modifier.padding(horizontal = 16.dp))
}
