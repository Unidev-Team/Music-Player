package uz.gita.music_player_io.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import uz.gita.music_player_io.utils.MusicListTypeConverter

/**
 *  Created by Nurlibay Koshkinbaev on 18/10/2022 23:29
 */

@Entity
data class PlaylistData(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String = "",
    val musicCount: Long = 0L,
    @TypeConverters(MusicListTypeConverter::class)
    var musicList: List<MusicData> = emptyList()
)