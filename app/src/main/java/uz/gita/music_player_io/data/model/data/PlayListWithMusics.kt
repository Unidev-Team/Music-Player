package uz.gita.music_player_io.data.model.data

import uz.gita.music_player_io.data.model.MusicData

// Created by Jamshid Isoqov an 10/19/2022
data class PlayListWithMusics(
    val musicData: MusicData,
    val isInPlaylist: Boolean
)
