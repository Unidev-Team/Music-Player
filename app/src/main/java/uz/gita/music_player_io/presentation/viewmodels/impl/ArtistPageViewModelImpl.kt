package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.data.model.ArtistData
import uz.gita.music_player_io.domain.ArtistUseCase
import uz.gita.music_player_io.presentation.viewmodels.ArtistPageViewModel
import javax.inject.Inject

@HiltViewModel
class ArtistPageViewModelImpl
@Inject constructor(
    private val useCase: ArtistUseCase
) : ArtistPageViewModel ,ViewModel(){
    override val artistFlow: MutableSharedFlow<List<ArtistData>> = MutableSharedFlow()
    override val clickItemFlow: MutableStateFlow<Unit> = MutableStateFlow(Unit)

    init {
        viewModelScope.launch {
            artistFlow.emit(useCase.getArtistList())
        }
    }

    override fun clickItemArtist() {
        viewModelScope.launch {
            clickItemFlow.emit(Unit)
        }
    }
}