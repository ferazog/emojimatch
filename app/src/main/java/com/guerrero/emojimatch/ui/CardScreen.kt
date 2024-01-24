package com.guerrero.emojimatch.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.guerrero.emojimatch.viewmodel.EmojisViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(
    viewModel: EmojisViewModel,
    onShare: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState()

    GridWithHeader(
        emojiCards = viewModel.emojis.value,
        onCardClick = { emojiCard ->
            viewModel.onCardClicked(emojiCard)
            if (viewModel.isGameFinished.value) {
                scope.launch { modalBottomSheetState.show() }
            }
        },
        onRestartClick = {
            viewModel.playAgain()
        }
    )
}