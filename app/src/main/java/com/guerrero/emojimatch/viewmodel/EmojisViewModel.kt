package com.guerrero.emojimatch.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guerrero.emojimatch.model.EmojiCard
import com.guerrero.emojimatch.model.LEVELS
import com.guerrero.emojimatch.model.Level
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class GameState(
    val loading: Boolean = true,
    val level: Level = Level(-1, "", false, emptyList()),
    val isLevelCompleted: Boolean = false
)

class EmojisViewModel : ViewModel() {

    private val _gameState = MutableStateFlow(GameState())

    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private var emojiCardSelected: EmojiCard? = null

    private var blockMultitouchFlag = true

    fun onCardClicked(emojiCard: EmojiCard) {
        if (blockMultitouchFlag) {
            viewModelScope.launch {
                blockMultitouchFlag = false
                rotateCard(emojiCard)
                blockMultitouchFlag = true
            }
        }
    }

    private suspend fun rotateCard(emojiCard: EmojiCard) {
        emojiCard.isRotated.value = true
        if (emojiCardSelected == null) {
            emojiCardSelected = emojiCard
        } else {
            if (emojiCard.emoji == emojiCardSelected?.emoji) {
                emojiCardSelected?.match()
                emojiCard.match()
                checkGameIsFinished()
                emojiCardSelected = null
            } else {
                delay(500)
                emojiCardSelected?.isRotated?.value = false
                emojiCard.isRotated.value = false
                emojiCardSelected = null
            }
        }
    }

    private fun checkGameIsFinished() {
        viewModelScope.launch {
            val emojis = gameState.value.level.emojis
            if (!emojis.any { !it.isMatched }) {
                _gameState.update { state ->
                    state.copy(isLevelCompleted = true)
                }
            }
        }
    }

    fun playAgain() {
        viewModelScope.launch {
            val emojis = gameState.value.level.emojis.map {
                it.copy(
                    isMatched = false,
                    isRotated = mutableStateOf(false)
                )
            }.shuffled()

            _gameState.update { state ->
                state.copy(
                    isLevelCompleted = false,
                    level = state.level.copy(emojis = emojis),

                    )
            }
        }
    }

    fun loadLevel(levelId: Int) {
        viewModelScope.launch {
            _gameState.update { state ->
                state.copy(
                    loading = false,
                    level = LEVELS.first { it.id == levelId }
                )
            }
        }
    }
}
