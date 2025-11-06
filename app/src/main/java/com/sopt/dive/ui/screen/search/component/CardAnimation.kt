package com.sopt.dive.ui.screen.search.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sopt.dive.R
import com.sopt.dive.utils.noRippleClickable
import kotlin.math.sqrt


@Composable
fun CardAnimation() {

    var isTextVisible by remember { mutableStateOf(false) }

    val animSpec = spring<Float>(
        dampingRatio = 20f / sqrt(4.0f * 177.8f * 1f),
        stiffness = 177.8f
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (isTextVisible) 1f else 0f,
        animationSpec = animSpec,
        label = "textAlpha"
    )

    val rotate by animateFloatAsState (
        targetValue = if (isTextVisible) 180f else 0f,
        animationSpec = animSpec,
        label = "rotate"
    )
    val zIndex by animateFloatAsState(
        targetValue = if (isTextVisible) -1f else 2f,
        animationSpec = animSpec,
        label = "zIndex"
    )
    val offset by animateDpAsState(
        targetValue = if (isTextVisible) 15.dp else 0.dp,
        label = "offset"
    )

    Box(
        modifier = Modifier
            .width(200.dp)
            .aspectRatio(0.75f)
            .zIndex(1f)
            .noRippleClickable { isTextVisible = !isTextVisible }
    ) {
        ImageCard(
            isTextVisible = isTextVisible,
            modifier = Modifier
                .zIndex(zIndex)
                .offset(offset,offset)
                .graphicsLayer {
                    rotationY = rotate
                    cameraDistance = 16 * density
                }
        )
        TextCard(
            isTextVisible = isTextVisible,
            textAlpha = textAlpha
        )
    }
}

@Composable
private fun TextCard(
    isTextVisible: Boolean,
    textAlpha: Float,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 80.dp,
            topEnd = 8.dp,
            bottomStart = 8.dp,
            bottomEnd = 80.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Magenta
        )
    ){
        Text(
            text = "asdfasldkfalskdjfhasdkfja;lsdjf;alksdfj;laks" +
                    "djf;alksjdflksjdfl;akjsdfasdfjalksjdfhalksdhfakldshfaklsjdfakjsdflakjsas" +
                    "askdjfalksdhfalksdhflakdsfhlaksdhflakjdsfhalkjsdfalsdjkfasd" +
                    "alksjdfhlaksjdalksdfhlaksdhfaksjdfhlaksdflakjdfalkjsdfh" +
                    "alksdjfhalksdjfhalskdjfalksjdflakjsdfhlkajdfakjd" +
                    "alksjdfhaskljdfhalksjfalksdj",
            modifier = modifier
                .alpha(textAlpha)
                .fillMaxSize(),
            color = Color.White
        )

    }
}


@Composable
private fun ImageCard(
    isTextVisible: Boolean,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(
            topStart = 8.dp,
            topEnd = 80.dp,
            bottomStart = 80.dp,
            bottomEnd = 8.dp
        )
    ){
        Image(
            painter = painterResource(R.drawable.card_back),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}

