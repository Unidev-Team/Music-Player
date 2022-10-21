package uz.gita.music_player_io.presentation.viewmodels.impl

import kotlinx.coroutines.flow.MutableStateFlow
import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.presentation.viewmodels.FolderDetailsViewModel

class FolderDetailsViewModelImpl : FolderDetailsViewModel {
    override val musicsByFolder = MutableStateFlow<List<MusicData>>(emptyList())

    override fun getAllMusicsByFolder(folderData: FolderData) {

    }
}