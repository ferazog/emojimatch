package com.guerrero.emojimatch.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guerrero.emojimatch.model.EmojiCard
import com.guerrero.emojimatch.model.emojiList
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EmojisViewModel : ViewModel() {

    var emojis: MutableState<List<EmojiCard>> = mutableStateOf(
        emojiList.shuffled()
    )

    var emojiCardSelected: EmojiCard? = null

    val isGameFinished: MutableState<Boolean> = mutableStateOf(false)

    private var attempts: Float = 0f

    private var successAttempts: Float = 0f

    val successRate: MutableState<Float> = mutableStateOf(0f)

    fun onCardClicked(emojiCard: EmojiCard) {
        emojiCard.isRotated.value = true
        if (emojiCardSelected == null) {
            emojiCardSelected = emojiCard
        } else {
            attempts += 1
            if (emojiCard.emoji == emojiCardSelected?.emoji) {
                successAttempts += 1
                emojiCardSelected?.match()
                emojiCard.match()
                checkGameIsFinished()
                emojiCardSelected = null
            } else {
                viewModelScope.launch {
                    delay(500)
                    emojiCardSelected?.isRotated?.value = false
                    emojiCard.isRotated.value = false
                    emojiCardSelected = null
                }
            }
        }
    }

    private fun checkGameIsFinished() {
        if (!emojis.value.any { !it.isMatched }) {
            successRate.value = (successAttempts / attempts) * 100f
            showGameFinished()
        }
    }

    private fun showGameFinished() {
        isGameFinished.value = true
    }

    fun playAgain() {
        attempts = 0f
        successAttempts = 0f
        successRate.value = 0f
        isGameFinished.value = false
        emojiList.forEach {
            it.isMatched = false
            it.isRotated.value = false
        }
        emojis.value = emojiList.shuffled()
    }
}
