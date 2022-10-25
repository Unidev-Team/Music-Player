package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.MusicData

interface FavouriteViewModel {

    val getAllFavouriteFlow: Flow<List<MusicData>>

    fun getAllFavouriteMusic()

    fun updateMusic(music: MusicData)

}