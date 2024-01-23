package com.guerrero.emojimatch.ui

import android.content.Intent
import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.guerrero.emojimatch.ui.theme.EmojiMatchTheme
import com.guerrero.emojimatch.viewmodel.EmojisViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var splashScreen: SplashScreen

    private val viewModel: EmojisViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
       // splashScreen = installSplashScreen()
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
    /*
    EmojiMatchBottomSheetComponent(
        onShare = {
            onShare()
        },
        onPlayAgain = {
            scope.launch { modalBottomSheetState.hide() }
            viewModel.playAgain()
        }
    )*/
/*
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
    }*/
}
