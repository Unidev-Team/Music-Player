package uz.gita.music_player_io.domain

import uz.gita.music_player_io.data.model.ArtistData

interface ArtistUseCase {

    suspend fun getArtistList():List<ArtistData>


}