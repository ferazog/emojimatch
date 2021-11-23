package com.guerrero.emojimatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guerrero.emojimatch.ui.theme.EmojiMatchTheme
import com.guerrero.emojimatch.ui.theme.Orange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmojiMatchTheme {
                Surface(color = MaterialTheme.colors.background) {
                    CardsScreen()
                }
            }
        }
    }
}

@Composable
fun CardsScreen(
    emojis: ArrayList<String> = arrayListOf(
        "😀",
        "🐶",
        "🍎",
        "⚽️",
        "🚙",
        "🖨",
        "💚",
        "🎉",
        "👏",
        "💃",
        "😀",
        "🐶",
        "🍎",
        "⚽️",
        "🚙",
        "🖨",
        "💚",
        "🎉",
        "👏",
        "💃"
    )
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            for (i in 1..5) {
                CardsRow(emojis = emojis)
            }
        }
    }
}

@Composable
fun CardsRow(emojis: ArrayList<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..4) {
            EmojiCard(emojis.removeAt(0))
        }
    }
}

@Composable
fun EmojiCard(emoji: String) {
    var rotated by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        animationSpec = tween(500)
    )

    val animateFront by animateFloatAsState(
        targetValue = if (!rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateBack by animateFloatAsState(
        targetValue = if (rotated) 1f else 0f,
        animationSpec = tween(500)
    )

    val animateColor by animateColorAsState(
        targetValue = if (rotated) Color.White else Orange,
        animationSpec = tween(500)
    )

    Card(
        elevation = 16.dp,
        modifier = Modifier
            .size(width = 80.dp, height = 128.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 8 * density
            }
            .clickable {
                rotated = !rotated
            },
        backgroundColor = animateColor
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = if (rotated) emoji else "",
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        alpha = if (rotated) animateBack else animateFront
                        rotationY = rotation
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EmojiMatchTheme {
        CardsScreen()
    }
}
