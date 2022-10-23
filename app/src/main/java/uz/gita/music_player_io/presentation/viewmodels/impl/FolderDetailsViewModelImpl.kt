package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.domain.FolderUseCase
import uz.gita.music_player_io.presentation.viewmodels.FolderDetailsViewModel
import javax.inject.Inject

@HiltViewModel
class FolderDetailsViewModelImpl @Inject constructor(private val useCase: FolderUseCase) : FolderDetailsViewModel, ViewModel() {

    override val musicsByFolder = MutableStateFlow<List<MusicData>>(emptyList())

    override fun getAllMusicsByFolder(folderData: FolderData) {
        viewModelScope.launch {
            musicsByFolder.emit(useCase.getAllMusicsByFolder(folderData.packageMusic))
        }
    }
}