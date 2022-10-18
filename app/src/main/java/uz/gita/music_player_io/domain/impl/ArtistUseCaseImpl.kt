package uz.gita.music_player_io.domain.impl

import uz.gita.music_player_io.data.model.ArtistData
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.room.dao.MusicDao
import uz.gita.music_player_io.domain.ArtistUseCase
import javax.inject.Inject

class ArtistUseCaseImpl @Inject constructor(
        private val dao: MusicDao

) : ArtistUseCase {
    override suspend fun getArtistList():List<ArtistData> = dao.getAllArtist()
    override suspend fun getAllSongsByArtistName(artistName: String): List<MusicData> {
       return dao.getAllSongsByArtist(artistName)
    }
}