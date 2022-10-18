package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.PlaylistData

interface PlayListViewModel {

    //val getAllPlaylistFlow: Flow<List<PlaylistData>>

    suspend fun addNewPlaylist(playlistData: PlaylistData)

    fun getAllPlaylist(): Flow<List<PlaylistData>>

}