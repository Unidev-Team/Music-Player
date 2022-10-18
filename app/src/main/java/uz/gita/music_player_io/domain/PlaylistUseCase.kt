package uz.gita.music_player_io.domain

import uz.gita.music_player_io.data.model.PlaylistData

interface PlaylistUseCase {

    suspend fun addNewPlaylist(playlistData: PlaylistData)

}