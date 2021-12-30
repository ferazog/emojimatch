package com.felipeerazog.emojimatch.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipeerazog.emojimatch.model.EMOJI_LIST
import com.felipeerazog.emojimatch.model.EmojiCard

@Composable
fun GridWithHeader(
    emojiCards: List<EmojiCard>,
    onCardClick: (EmojiCard) -> Unit,
    onRestartClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderBox {
            onRestartClick()
        }
        Grid(emojiCards) { emojiCard ->
            onCardClick(emojiCard)
        }
    }
}

@Composable
fun HeaderBox(
    onRestartClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = {
                onRestartClick()
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                Icons.Filled.Refresh,
                contentDescription = "Restart"
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Grid(
    emojiCards: List<EmojiCard>,
    onCardClick: (EmojiCard) -> Unit
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(count = 4),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(emojiCards) { emojiCard ->
            EmojiCardComponent(emojiCard = emojiCard) {
                onCardClick(emojiCard)
            }
        }
    }
}

@Composable
@Preview
fun PreviewHeaderBox() {
    GridWithHeader(
        EMOJI_LIST,
        {},
        {}
    )
}
