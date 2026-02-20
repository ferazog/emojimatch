package com.felipeerazog.emojimatch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felipeerazog.emojimatch.R
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
                painter = painterResource(R.drawable.round_refresh_24),
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
        columns = GridCells.Fixed(4),
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
