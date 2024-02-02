package com.guerrero.emojimatch.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guerrero.emojimatch.ui.theme.EmojiMatchTheme
import com.guerrero.emojimatch.viewmodel.EmojisViewModel

const val EXTRA_LEVEL_ID = "EXTRA_LEVEL_ID"

class GameActivity : ComponentActivity() {

    private val viewModel: EmojisViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractLevelIdFromExtras()
        setContent {
            EmojiMatchTheme {
                CardsScreen(
                    gameState = viewModel
                        .gameState
                        .collectAsStateWithLifecycle()
                        .value,
                    onCardClicked = { emojiCard -> viewModel.onCardClicked(emojiCard) },
                    onRestartClicked = { viewModel.playAgain() }
                )
            }
        }
    }

    private fun extractLevelIdFromExtras() {
        val levelId = intent.getIntExtra(EXTRA_LEVEL_ID, -1)
        if (levelId != -1) {
            viewModel.loadLevel(levelId)
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