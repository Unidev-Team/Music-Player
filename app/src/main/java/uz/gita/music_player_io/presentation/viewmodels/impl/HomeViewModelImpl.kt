package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.model.data.PlayListWithMusics
import uz.gita.music_player_io.data.room.dao.MusicDao
import uz.gita.music_player_io.domain.MusicsUseCase
import uz.gita.music_player_io.domain.PlaylistUseCase
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val musicsUseCase: MusicsUseCase,
    private val playlistUseCase: PlaylistUseCase,
    private val musicDao: MusicDao
) : HomeViewModel, ViewModel() {

    private var playListId: Int = 0

    override fun getAllMusics(): Flow<List<PlayListWithMusics>> = playlistUseCase.getMusicsWithPlaylist(playListId)

    override fun getAllMusicsPlaylist(): Flow<List<MusicData>> = musicsUseCase.getAllMusics()

    override fun refreshAllMusics() {
        viewModelScope.launch {
            musicsUseCase.refreshMusics()
        }
    }

    override fun changeMusic(playListWithMusics: PlayListWithMusics) {
        viewModelScope.launch(Dispatchers.IO) {
            val playlist = musicDao.getPlayListById(playListId)
            val newList = playlist.musicList.toMutableList()
            if (playListWithMusics.isInPlaylist) {
                newList.removeIf {
                    it.title == playListWithMusics.musicData.title &&
                    it.artistName == playListWithMusics.musicData.artistName
                }
            } else {
                newList.add(playListWithMusics.musicData)
            }
            musicDao.updatePlayList(playlist.copy(musicList = newList))
        }
    }

    override fun setPlaylistId(id: Int) {
        playListId = id
    }

    override fun updateMusic(musicData: MusicData) {
        viewModelScope.launch {
            musicsUseCase.updateMusic(musicData)
        }
    }
}