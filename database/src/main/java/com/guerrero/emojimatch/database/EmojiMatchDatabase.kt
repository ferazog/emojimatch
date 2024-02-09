package com.guerrero.emojimatch.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        LevelEntity::class
    ]
)
abstract class EmojiMatchDatabase : RoomDatabase() {
    abstract fun levelDao(): LevelDao
}
