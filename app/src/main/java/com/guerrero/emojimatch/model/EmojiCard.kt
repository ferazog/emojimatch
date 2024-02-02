package com.guerrero.emojimatch.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class EmojiCard(
    val emoji: String,
    var isRotated: MutableState<Boolean> = mutableStateOf(false),
    var isMatched: Boolean = false
) {

    fun match() {
        isMatched = true
    }
}
