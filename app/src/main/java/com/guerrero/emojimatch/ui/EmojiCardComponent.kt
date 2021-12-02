package com.guerrero.emojimatch.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guerrero.emojimatch.model.EmojiCard
import com.guerrero.emojimatch.ui.theme.EmojiMatchTheme
import com.guerrero.emojimatch.ui.theme.Orange

@Composable
fun EmojiCardComponent(emojiCard: EmojiCard, onRotate: () -> Unit) {
    val isRotated = emojiCard.isRotated.value
    val rotation by animateFloatAsState(
        targetValue = if (isRotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!isRotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (isRotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateColor by animateColorAsState(
        targetValue = if (isRotated) Color.White else Orange,
        animationSpec = tween(500)
    )

    Card(
        elevation = 8.dp,
        backgroundColor = animateColor,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable {
                if (!isRotated) {
                    onRotate()
                }
            }
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
                this.transformOrigin
            }
    ) {
        Text(
            text = if (isRotated) emojiCard.emoji else "",
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 32.dp)
                .graphicsLayer {
                    alpha = if (isRotated) animateBack else animateFront
                    rotationY = rotation
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EmojiCardComponentPreview() {
    EmojiMatchTheme {
        EmojiCardComponent(
            EmojiCard(
                emoji = "😀",
                isRotated = remember { mutableStateOf(true) }
            )
        ) {}
    }
}
