package uz.gita.music_player_io.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.PlaylistData
import uz.gita.music_player_io.data.model.data.PlayListWithMusics

interface PlaylistUseCase {

    suspend fun addNewPlaylist(playlistData: PlaylistData)

    fun getAllPlaylist(): Flow<List<PlaylistData>>

    fun getPlaylistMusic(id: Int): Flow<PlaylistData>

    fun getMusicsWithPlaylist(id: Int): Flow<List<PlayListWithMusics>>

    suspend fun changePlayList(playlistData: PlaylistData)

}