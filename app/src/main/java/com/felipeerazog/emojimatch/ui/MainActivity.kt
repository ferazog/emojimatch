package com.felipeerazog.emojimatch.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.felipeerazog.emojimatch.ui.theme.EmojiMatchTheme
import com.felipeerazog.emojimatch.viewmodel.EmojisViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    val viewModel: EmojisViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmojiMatchTheme {
                CardsScreen(viewModel) {
                    shareMyScore()
                }
            }
        }
    }

    private fun shareMyScore() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                "Try EmojiMatch game! https://play.google.com/store/apps/details?id=com.guerrero.emojimatch"
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardsScreen(
    viewModel: EmojisViewModel,
    onShare: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            EmojiMatchBottomSheetComponent(
                onShare = {
                    onShare()
                },
                onPlayAgain = {
                    scope.launch { showBottomSheet = false }
                    viewModel.playAgain()
                }
            )
        }
    }
    GridWithHeader(
        emojiCards = viewModel.emojis.value,
        onCardClick = { emojiCard ->
            viewModel.onCardClicked(emojiCard)
            if (viewModel.isGameFinished.value) {
                scope.launch { showBottomSheet = true }
            }
        },
        onRestartClick = {
            viewModel.playAgain()
        }
    )
}
