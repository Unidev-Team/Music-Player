package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.model.data.PlayListWithMusics

interface HomeViewModel {

    fun getAllMusics(): Flow<List<PlayListWithMusics>>

    fun getAllMusicsPlaylist(): Flow<List<MusicData>>

    fun refreshAllMusics()

    fun changeMusic(playListWithMusics: PlayListWithMusics)

    fun setPlaylistId(id: Int)
}