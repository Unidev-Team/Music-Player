package uz.gita.music_player_io.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import uz.gita.music_player_io.utils.MusicListTypeConverter

/**
 *  Created by Nurlibay Koshkinbaev on 18/10/2022 23:29
 */

@Entity
@Parcelize
data class PlaylistData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String = "",
    val musicCount: Long = 0L,
    @TypeConverters(MusicListTypeConverter::class)
    var musicList: List<MusicData> = emptyList()
): Parcelable