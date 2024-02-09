package com.guerrero.emojimatch.di

import android.content.Context
import androidx.room.Room
import com.guerrero.emojimatch.database.EmojiMatchDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): EmojiMatchDatabase {
        return Room.databaseBuilder(
            context,
            EmojiMatchDatabase::class.java,
            "emoji-database"
        ).build()
    }
}
