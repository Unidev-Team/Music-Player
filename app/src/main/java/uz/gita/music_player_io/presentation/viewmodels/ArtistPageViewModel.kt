package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import uz.gita.music_player_io.data.model.ArtistData


interface ArtistPageViewModel {

    val artistFlow: SharedFlow<List<ArtistData>>
    val clickItemFlow: Flow<Unit>
    fun clickItemArtist()
}