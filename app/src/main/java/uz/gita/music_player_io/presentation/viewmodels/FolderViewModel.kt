package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.FolderData

// Created by Jamshid Isoqov an 10/21/2022
interface FolderViewModel {

    val getAllFolders:Flow<List<FolderData>>

}