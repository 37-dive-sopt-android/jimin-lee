package com.sopt.dive.ui.component

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R

@Composable
fun HomeItem (
    img: Int,
    name: String,
    message: String,
    birth: Birth,
    content: Content,
    etc: Etc,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(7.dp))
            .padding(horizontal = 7.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            painter = painterResource(img),
            contentDescription = stringResource(R.string.main_profileimg),
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(0.5.dp, Color.LightGray, RoundedCornerShape(20.dp))
        )

        HomeNameItem(
            name = name,
            message = message,
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
fun HomeNameItem (
    name: String,
    message: String,
    birth: Birth,
    content: Content,
) {
    val birthArea = remember {
        when (birth) {
            Birth.NONE -> null
            Birth.BIRTH -> R.drawable.ic_cake
        }
    }
    val contentArea = remember {
        when (content) {
            Content.NONE -> null
            Content.Exist -> message
        }
    }

    Column (
        modifier = Modifier
            .padding(start = 15.dp)
        ){

        Row(
            modifier = Modifier.width(160.dp),
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
fun HomeEtcItem (
    etc: Etc
) {
    when (etc) {
        is Etc.None -> null
        is Etc.Music -> {
            Button (
                onClick = {},
                modifier = Modifier.height(30.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, Color.Green),
                contentPadding = PaddingValues(horizontal = 10.dp)
            ){
                Text(
                    text = etc.music,
                    modifier = Modifier.weight(1f, fill = false),
                    fontSize = 10.sp,
                    color = Color.DarkGray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Image(
                    painter = painterResource(R.drawable.ic_music),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(15.dp),
                    colorFilter  = ColorFilter.tint(Color.Green)

                )
            }
        }
        is Etc.Gift -> {
            Button (
                onClick = {},
                modifier = Modifier.height(30.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, Color.Red),
                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp)
            ){
                Text(
                    text = "선물하기",
                    modifier = Modifier.padding(end = 3.dp),
                    fontSize = 10.sp,
                    color = Color.DarkGray,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Image(
                    painter = painterResource(R.drawable.ic_gift),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(15.dp),
                    colorFilter  = ColorFilter.tint(Color.Red)

                )
            }
        }
    }
}

enum class Birth {
    NONE, BIRTH
}

enum class Content {
    NONE, Exist
}

sealed class Etc {
    data object None: Etc()
    data class Music(val music: String) : Etc()
    data object Gift: Etc()
}


@Preview(showBackground = true)
@Composable
private fun HomeItemPreview() {
    HomeItem(R.drawable.profile,"이지민", "", Birth.BIRTH, Content.NONE,Etc.Music("dsfsfsdfsd"))
}
