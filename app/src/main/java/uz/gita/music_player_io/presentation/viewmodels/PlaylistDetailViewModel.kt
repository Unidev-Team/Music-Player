package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import uz.gita.music_player_io.data.model.PlaylistData

interface PlaylistDetailViewModel {

    val playlistSongFlow: SharedFlow<PlaylistData>

    fun getAllPlaylistSong(id: Int)

}