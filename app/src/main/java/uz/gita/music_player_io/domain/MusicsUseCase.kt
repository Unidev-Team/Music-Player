package uz.gita.music_player_io.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.MusicData

// Created by Jamshid Isoqov an 10/7/2022
interface MusicsUseCase {

    suspend fun updateMusic(musicData: MusicData)

    suspend fun deleteMusic(musicData: MusicData)

    suspend fun refreshMusics()

    fun getAllMusics():Flow<List<MusicData>>

}