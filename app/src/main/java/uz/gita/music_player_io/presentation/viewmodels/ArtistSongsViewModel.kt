package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.MusicData

interface ArtistSongsViewModel  {
    val songsByArtisFlow:Flow<List<MusicData>>
    fun getSongsByArtist(artistName:String)
}