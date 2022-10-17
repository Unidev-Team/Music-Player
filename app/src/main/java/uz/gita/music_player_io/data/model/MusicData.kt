package uz.gita.music_player_io.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

// Created by Jamshid Isoqov an 10/7/2022
@Entity(tableName = "musics")
data class MusicData(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val artistName: String,
    val album: String,
    val title: String,
    val displayName: String,
    val duration: Long,
    val image: String,
    val path: String
) : Serializable
