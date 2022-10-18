package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import uz.gita.music_player_io.data.model.PlaylistData
import uz.gita.music_player_io.domain.PlaylistUseCase
import uz.gita.music_player_io.presentation.viewmodels.PlayListViewModel
import javax.inject.Inject

@HiltViewModel
class PlayListViewModelImpl @Inject constructor(
    private val useCase: PlaylistUseCase
) : PlayListViewModel, ViewModel() {

   // override val getAllPlaylistFlow: MutableStateFlow<List<PlaylistData>> = MutableStateFlow(emptyList())

    override suspend fun addNewPlaylist(playlistData: PlaylistData) {
        useCase.addNewPlaylist(playlistData)
    }

    override fun getAllPlaylist(): Flow<List<PlaylistData>> {
        return useCase.getAllPlaylist()
    }

}