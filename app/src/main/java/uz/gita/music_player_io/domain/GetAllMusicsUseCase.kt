package uz.gita.music_player_io.domain

import uz.gita.music_player_io.data.model.MusicData

// Created by Jamshid Isoqov an 10/7/2022
interface GetAllMusicsUseCase {
    suspend fun getAllMusics(): List<MusicData>
}