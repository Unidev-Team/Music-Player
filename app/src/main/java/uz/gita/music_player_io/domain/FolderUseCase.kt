package uz.gita.music_player_io.domain

import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.data.model.MusicData

// Created by Jamshid Isoqov an 10/21/2022
interface FolderUseCase {

    suspend fun getFolders(): List<FolderData>

    suspend fun getAllMusicsByFolder(path: String): List<MusicData>


}