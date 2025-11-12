package com.sopt.dive.ui.screen.search.component

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sopt.dive.R
import com.sopt.dive.utils.noRippleClickable
import kotlin.math.sqrt


@Composable
fun CardSpring() {

    var isTextVisible by remember { mutableStateOf(false) }

    val transition = updateTransition(isTextVisible, label = "transition")

    val dampingRatio = 20f / sqrt(4.0f * 177.8f * 1f)

    val animSpec = spring<Float>(
        dampingRatio = dampingRatio,
        stiffness = 177.8f
    )
    val animDpSpec = spring<Dp>(
        dampingRatio = dampingRatio,
        stiffness = 177.8f
    )

    val textAlpha by transition.animateFloat(
        label = "textAlpha",
        transitionSpec = { animSpec }
    ) { visible ->
        if (visible) 1f else 0f
    }

    val rotate by transition.animateFloat(
        label = "rotate",
        transitionSpec = { animSpec }
    ) { visible ->
        if (visible) 180f else 0f
    }

    val zIndex by transition.animateFloat(
        label = "zIndex",
        transitionSpec = { animSpec }
    ) { visible ->
        if (visible) -5f else 5f
    }

    val offset by transition.animateDp(
        label = "offset",
        transitionSpec = { animDpSpec }
    ) { visible ->
        if (visible) 20.dp else 0.dp
    }

    val textElevation by transition.animateDp(
        label = "textElevation",
        transitionSpec = { animDpSpec }
    ) { visible ->
        if (visible) 7.dp else 0.dp
    }

    val imageElevation by transition.animateDp(
        label = "imageElevation",
        transitionSpec = { animDpSpec }
    ) { visible ->
        if (visible) 0.dp else 7.dp
    }

    val changeImageElevation = if (rotate <= 20f) imageElevation else 0.dp

    val textShape = RoundedCornerShape(
        topStart = 80.dp,
        topEnd = 8.dp,
        bottomStart = 8.dp,
        bottomEnd = 80.dp
    )

    val imageShape = RoundedCornerShape(
        topStart = 8.dp,
        topEnd = 80.dp,
        bottomStart = 80.dp,
        bottomEnd = 8.dp
    )

    Box(
        modifier = Modifier
            .width(200.dp)
            .aspectRatio(0.75f)
            .noRippleClickable { isTextVisible = !isTextVisible }
    ) {
        ImageCard(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(zIndex)
                .offset(offset, offset)
                .shadow(
                    elevation = changeImageElevation,
                    shape = imageShape
                )
                .clip(imageShape)
                .graphicsLayer {
                    rotationY = rotate
                    cameraDistance = 16 * density
                }
        )
        TextCard(
            textAlpha = textAlpha,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1f)
                .shadow(
                    elevation = textElevation,
                    shape = textShape
                )
                .background(
                    color = Color(255,90,140),
                    shape = textShape
                )
        )
    }
}

@Composable
private fun TextCard(
    textAlpha: Float,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
    ){
        Text(
            text = "마루는강쥐".repeat(80),
            modifier = Modifier
                .fillMaxSize()
                .alpha(textAlpha),
            color = Color.White
        )
    }
}

@Composable
private fun ImageCard(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
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

