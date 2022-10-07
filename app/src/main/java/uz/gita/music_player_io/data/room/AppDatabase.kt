package uz.gita.music_player_io.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.room.dao.MusicDao

// Created by Jamshid Isoqov an 10/7/2022
@Database(entities = [MusicData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao
}