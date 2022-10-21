package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.domain.FolderUseCase
import uz.gita.music_player_io.presentation.viewmodels.FolderViewModel
import javax.inject.Inject

@HiltViewModel
class FolderViewModelImpl @Inject constructor(
    private val folderUseCase: FolderUseCase
) : FolderViewModel, ViewModel() {

    override val getAllFolders = MutableStateFlow<List<FolderData>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFolders.emit(folderUseCase.getFolders())
        }
    }
}