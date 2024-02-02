package com.guerrero.emojimatch.model

data class Level(
    val id:Int,
    val title: String = "Level $id",
    val locked: Boolean,
    val emojis: List<EmojiCard>
)

val LEVELS = listOf(
    Level(
        id = 1,
        locked = false,
        emojis = listOf(
            EmojiCard("😀"),
            EmojiCard("😀"),
            EmojiCard("🍎"),
            EmojiCard("🍎"),
            EmojiCard("⚽️"),
            EmojiCard("🐶"),
            EmojiCard("🚙"),
            EmojiCard("🖨"),
            EmojiCard("💚"),
            EmojiCard("🎉"),
            EmojiCard("👏"),
            EmojiCard("💃"),
            EmojiCard("🐶"),
            EmojiCard("⚽️"),
            EmojiCard("🚙"),
            EmojiCard("🖨"),
            EmojiCard("💚"),
            EmojiCard("🎉"),
            EmojiCard("👏"),
            EmojiCard("💃")
        )
    ),
    Level(
        id = 2,
        locked = false,
        emojis = listOf(
            EmojiCard("😀"),
            EmojiCard("😀"),
            EmojiCard("🍎"),
            EmojiCard("🍎"),
            EmojiCard("⚽️"),
            EmojiCard("🐶"),
            EmojiCard("🚙"),
            EmojiCard("🖨"),
            EmojiCard("💚"),
            EmojiCard("🎉"),
            EmojiCard("👏"),
            EmojiCard("💃"),
            EmojiCard("🐶"),
            EmojiCard("⚽️"),
            EmojiCard("🚙"),
            EmojiCard("🖨"),
            EmojiCard("💚"),
            EmojiCard("🎉"),
            EmojiCard("👏"),
            EmojiCard("💃")
        )
    )
)
