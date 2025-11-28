package com.sopt.dive.ui.screen.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sopt.dive.R
import com.sopt.dive.ui.screen.home.type.HomeListType

@Composable
fun HomeItem (
    img: String,
    name: String,
    message: String?,
    birth: HomeListType.Birth,
    content: HomeListType.Content,
    etc: HomeListType.Etc,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(7.dp))
            .padding(horizontal = 7.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            model = img,
            contentDescription = stringResource(R.string.main_profileimg),
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(0.5.dp, Color.LightGray, RoundedCornerShape(20.dp))
        )

        HomeNameItem(
            name = name,
            message = message.orEmpty(),
            birth = birth,
            content = content
        )

        Spacer(modifier = Modifier.weight(1f))

        HomeEtcItem(
            etc = etc,
        )
    }
}

@Composable
private fun HomeNameItem (
    name: String,
    message: String,
    birth: HomeListType.Birth,
    content: HomeListType.Content,
) {
    val birthArea = remember {
        when (birth) {
            HomeListType.Birth.NONE -> null
            HomeListType.Birth.BIRTH -> R.drawable.ic_cake
        }
    }
    val contentArea = remember {
        when (content) {
            HomeListType.Content.NONE -> null
            HomeListType.Content.Exist -> message
        }
    }

    Column (
        modifier = Modifier
            .padding(start = 15.dp)
        ){

        Row(
            modifier = Modifier.width(150.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                modifier = Modifier,
                fontSize = 15.sp
            )

            birthArea?.let { image ->
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(16.dp)
                )

            }
        }

        contentArea?.let { content ->
            Text(
                text = content,
                modifier = Modifier
                    .padding(top = 3.dp, end = 5.dp)
                    .fillMaxWidth(0.5f),
                fontSize = 13.sp,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

    }
}

@Composable
private fun HomeEtcItem (
    etc: HomeListType.Etc
) {
    val etcText = when(etc) {
        is HomeListType.Etc.Music -> etc.music
        is HomeListType.Etc.Gift -> stringResource(R.string.btn_gift)
        is HomeListType.Etc.None -> return
    }

    val etcImage = when(etc) {
        is HomeListType.Etc.Music -> painterResource(R.drawable.ic_music)
        is HomeListType.Etc.Gift -> painterResource(R.drawable.ic_gift)
        is HomeListType.Etc.None -> return
    }

    val etcColor = when(etc) {
        is HomeListType.Etc.Music -> Color.Green
        is HomeListType.Etc.Gift -> Color.Red
        is HomeListType.Etc.None -> return
    }

    Button (
        onClick = {},
        modifier = Modifier.height(30.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        border = BorderStroke(1.dp, etcColor),
        contentPadding = PaddingValues(horizontal = 10.dp)
    ){
        Text(
            text = etcText,
            modifier = Modifier.weight(1f, fill = false),
            fontSize = 10.sp,
            color = Color.DarkGray,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        Image(
            painter = etcImage,
            contentDescription = null,
            modifier = Modifier
                .padding(start = 5.dp)
                .size(15.dp),
            colorFilter  = ColorFilter.tint(etcColor)
        )
    }
}



@Preview(showBackground = true)
@Composable
private fun HomeItemPreview() {
    HomeItem("R.drawable.profile","이지민", "", HomeListType.Birth.BIRTH, HomeListType.Content.NONE, HomeListType.Etc.Music("dsfsfsdfsd"))
}
