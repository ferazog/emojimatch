package com.guerrero.emojimatch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guerrero.emojimatch.model.EmojiCard
import com.guerrero.emojimatch.model.LEVELS
import com.guerrero.emojimatch.model.Level

@Composable
fun GridWithHeader(
    level: Level,
    onCardClick: (EmojiCard) -> Unit,
    onRestartClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderBox(
            title = level.title,
            onRestartClick = { onRestartClick() }
        )
        Grid(level.emojis) { emojiCard ->
            onCardClick(emojiCard)
        }
    }
}

@Composable
fun HeaderBox(
    title: String,
    onRestartClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = {
                onRestartClick()
            }
        ) {
            Icon(
                Icons.Filled.Refresh,
                contentDescription = "Restart"
            )
        }
    }
}

@Composable
fun Grid(
    emojiCards: List<EmojiCard>,
    onCardClick: (EmojiCard) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 4),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(emojiCards.size) { index ->
            EmojiCardComponent(emojiCard = emojiCards[index]) {
                onCardClick(emojiCards[index])
            }
        }
    }
}

@Composable
@Preview
fun PreviewHeaderBox() {
    GridWithHeader(
        level = LEVELS[0],
        onCardClick = {},
        onRestartClick = {}
    )
}
