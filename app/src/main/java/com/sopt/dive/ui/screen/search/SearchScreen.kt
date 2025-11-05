package com.sopt.dive.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R

@Composable
fun SearchScreen(
    innerPadding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        Text(
            text = stringResource(R.string.title_Search),
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