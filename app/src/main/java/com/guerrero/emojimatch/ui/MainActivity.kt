package com.guerrero.emojimatch.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.guerrero.emojimatch.model.EMOJI_LIST
import com.guerrero.emojimatch.model.EmojiCard
import com.guerrero.emojimatch.ui.theme.EmojiMatchTheme
import com.guerrero.emojimatch.viewmodel.EmojisViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmojiMatchTheme {
                CardsScreen {
                    shareMyScore()
                }
            }
        }
    }

    private fun shareMyScore() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Try EmojiMatch app!")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardsScreen(
    viewModel: EmojisViewModel = viewModel(),
    onShare: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val modalBottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    ModalBottomSheetLayout(
        sheetContent = {
            EmojiMatchBottomSheetComponent(
                onShare = {
                    onShare()
                },
                onPlayAgain = {
                    scope.launch { modalBottomSheetState.hide() }
                    viewModel.playAgain()
                }
            )
        },
        sheetState = modalBottomSheetState
    ) {
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
}

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
