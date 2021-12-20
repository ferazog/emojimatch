package com.guerrero.emojimatch.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guerrero.emojimatch.model.EMOJI_LIST
import com.guerrero.emojimatch.model.EmojiCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class EmojisViewModel : ViewModel() {

    var emojis: MutableState<List<EmojiCard>> = mutableStateOf(
        EMOJI_LIST.shuffled()
    )

    var emojiCardSelected: EmojiCard? = null

    val isGameFinished: MutableState<Boolean> = mutableStateOf(false)

    fun onCardClicked(emojiCard: EmojiCard) {
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
            showGameFinished()
        }
    }

    private fun showGameFinished() {
        isGameFinished.value = true
    }

    fun playAgain() {
        isGameFinished.value = false
        EMOJI_LIST.forEach {
            it.isMatched = false
            it.isRotated.value = false
        }
        emojis.value = EMOJI_LIST.shuffled()
    }
}
