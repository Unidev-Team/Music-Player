package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.MusicData

interface HomeViewModel {

    fun getAllMusics(): Flow<List<MusicData>>
}