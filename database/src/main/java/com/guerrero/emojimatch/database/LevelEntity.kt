package com.guerrero.emojimatch.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val LEVEL_TABLE_NAME = "level"

@Entity(tableName = LEVEL_TABLE_NAME)
data class LevelEntity(
    @PrimaryKey val id: Int,
    val title: String = "Level $id",
    val locked: Boolean
)
