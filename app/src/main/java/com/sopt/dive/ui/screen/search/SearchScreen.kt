package com.sopt.dive.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.ui.screen.search.component.CardFlip
import com.sopt.dive.ui.screen.search.component.CardAnimation

@Composable
fun SearchScreen(
    innerPadding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CardFlip()
            Spacer(modifier = Modifier.height(50.dp))
            CardAnimation()
        }

    }
}

@Preview
@Composable
private fun SearchScreenPreview() {
    SearchScreen(PaddingValues())
}