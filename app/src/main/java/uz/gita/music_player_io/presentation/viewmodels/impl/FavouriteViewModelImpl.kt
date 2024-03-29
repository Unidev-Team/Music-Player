package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.domain.MusicsUseCase
import uz.gita.music_player_io.presentation.viewmodels.FavouriteViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModelImpl @Inject constructor(private val useCase: MusicsUseCase) :
    FavouriteViewModel, ViewModel() {

    override val getAllFavouriteFlow: MutableStateFlow<List<MusicData>> = MutableStateFlow(emptyList())

    override fun getAllFavouriteMusic() {
        viewModelScope.launch {
            useCase.getAllFavouriteMusic().collect {
                getAllFavouriteFlow.emit(it)
            }
        }
    }

    override fun updateMusic(music: MusicData) {
        viewModelScope.launch {
            useCase.updateMusic(music)
        }
    }
}