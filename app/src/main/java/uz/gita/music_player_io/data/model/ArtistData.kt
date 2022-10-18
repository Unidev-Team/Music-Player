package uz.gita.music_player_io.data.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistData(
    val artistName: String,
    val image: String,
    val count: Int
):Parcelable