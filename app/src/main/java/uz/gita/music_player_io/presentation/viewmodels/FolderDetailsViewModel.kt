package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.data.model.MusicData

// Created by Jamshid Isoqov an 10/21/2022
interface FolderDetailsViewModel {

    val musicsByFolder: Flow<List<MusicData>>

    fun getAllMusicsByFolder(folderData: FolderData)

}