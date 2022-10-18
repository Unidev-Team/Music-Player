package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.domain.ArtistUseCase
import uz.gita.music_player_io.presentation.viewmodels.ArtistSongsViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistSongsViewModelImpl
@Inject constructor(
    private val useCase: ArtistUseCase
) : ArtistSongsViewModel, ViewModel() {
    override val songsByArtisFlow = MutableStateFlow(emptyList<MusicData>())

    override fun getSongsByArtist(artistName: String) {
        viewModelScope.launch {
            songsByArtisFlow.emit(useCase.getAllSongsByArtistName(artistName))
        }
    }
}