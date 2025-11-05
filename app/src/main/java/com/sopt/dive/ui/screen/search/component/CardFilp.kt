package com.sopt.dive.ui.screen.search.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sopt.dive.R

@Composable
fun CardFlip() {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        )
    )

    Card(
        onClick = { isFlipped = !isFlipped },
        modifier = Modifier
            .width(200.dp)
            .aspectRatio(0.75f)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 16 * density
            }
    ) {
        if (rotation <= 90f) {
            CardFront()
        } else {
            CardBack(
                modifier = Modifier.graphicsLayer {
                    rotationY = 180f
                }
            )
        }
    }
}

@Composable
private fun CardFront(){
    Image(
        painter = painterResource(R.drawable.card_front),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun CardBack(
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(R.drawable.card_back),
        contentDescription = null,
        modifier = modifier.fillMaxSize(),
        contentScale = ContentScale.Crop

    )
}