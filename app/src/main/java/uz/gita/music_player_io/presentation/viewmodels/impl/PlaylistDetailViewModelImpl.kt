package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.data.model.PlaylistData
import uz.gita.music_player_io.domain.PlaylistUseCase
import uz.gita.music_player_io.presentation.viewmodels.PlaylistDetailViewModel
import javax.inject.Inject

@HiltViewModel
class PlaylistDetailViewModelImpl @Inject constructor(private val useCase: PlaylistUseCase) :
    PlaylistDetailViewModel, ViewModel() {

    override val playlistSongFlow: MutableSharedFlow<PlaylistData> = MutableSharedFlow()

    override fun getAllPlaylistSong(id: Int) {
        viewModelScope.launch {
            useCase.getPlaylistMusic(id).collect {
                playlistSongFlow.emit(it)
            }
        }
    }
}