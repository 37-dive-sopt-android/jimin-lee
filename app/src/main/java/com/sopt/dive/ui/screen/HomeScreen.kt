package com.sopt.dive.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.data.homeItemList
import com.sopt.dive.ui.component.HomeItem

@Composable
fun HomeScreen(
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.title_home),
                    modifier = Modifier.padding(bottom = 10.dp),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            items(homeItemList) { item ->
                HomeItem(
                    img = item.img,
                    name = item.name,
                    message = item.message,
                    birth = item.birth,
                    content = item.content,
                    etc = item.etc
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
