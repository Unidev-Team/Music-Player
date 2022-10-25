package uz.gita.music_player_io.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.model.PlaylistData
import uz.gita.music_player_io.data.room.dao.MusicDao
import uz.gita.music_player_io.utils.MusicListTypeConverter

// Created by Jamshid Isoqov an 10/7/2022
@Database(entities = [MusicData::class, PlaylistData::class], version = 7)
@TypeConverters(MusicListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun musicDao(): MusicDao
}