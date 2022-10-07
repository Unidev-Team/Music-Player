package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.domain.MusicsUseCase
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val musicsUseCase: MusicsUseCase
) : HomeViewModel, ViewModel() {
    override fun getAllMusics(): Flow<List<MusicData>> {
        return musicsUseCase.getAllMusics()
    }
}