package uz.gita.music_player_io.presentation.viewmodels

import uz.gita.music_player_io.data.model.PlaylistData

interface PlayListViewModel {

    suspend fun addNewPlaylist(playlistData: PlaylistData)

}