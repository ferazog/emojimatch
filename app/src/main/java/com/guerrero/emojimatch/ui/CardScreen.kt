package com.guerrero.emojimatch.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.guerrero.emojimatch.model.EmojiCard
import com.guerrero.emojimatch.viewmodel.GameState

@Composable
fun CardsScreen(
    gameState: GameState,
    onCardClicked: (EmojiCard) -> Unit,
    onRestartClicked: () -> Unit
) {
    val openAlertDialog = remember { mutableStateOf(gameState.isLevelCompleted) }

    Box(modifier = Modifier.fillMaxSize()) {
        GridWithHeader(
            level = gameState.level,
            onCardClick = { emojiCard ->
                onCardClicked(emojiCard)
            },
            onRestartClick = { onRestartClicked() }
        )
        if (openAlertDialog.value) {
            LevelCompletedMessage { openAlertDialog.value = false }
        }
    }
}

@Composable
fun LevelCompletedMessage(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "Congrats!",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(),
                textAlign = TextAlign.Center,
            )
        }
    }
}