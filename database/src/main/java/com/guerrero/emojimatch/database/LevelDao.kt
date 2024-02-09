package com.guerrero.emojimatch.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LevelDao {

    @Query("SELECT * FROM $LEVEL_TABLE_NAME")
    fun getAll(): List<LevelEntity>

    @Query("SELECT * FROM $LEVEL_TABLE_NAME WHERE id = :key LIMIT 1")
    fun loadById(key: Int): LevelEntity

    @Insert
    fun insertAll(vararg levels: LevelEntity)

    @Delete
    fun delete(levelEntity: LevelEntity)
}
