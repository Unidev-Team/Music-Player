package uz.gita.music_player_io.domain

import uz.gita.music_player_io.data.model.ArtistData
import uz.gita.music_player_io.data.model.MusicData

interface ArtistUseCase {

    suspend fun getArtistList():List<ArtistData>

    suspend fun getAllSongsByArtistName(artistName:String):List<MusicData>


}