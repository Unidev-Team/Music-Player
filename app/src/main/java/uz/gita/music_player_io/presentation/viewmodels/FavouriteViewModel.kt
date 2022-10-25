package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.MusicData

interface FavouriteViewModel {

    fun getAllFavouriteMusic(): Flow<List<MusicData>>

    fun updateMusic(music: MusicData)

}