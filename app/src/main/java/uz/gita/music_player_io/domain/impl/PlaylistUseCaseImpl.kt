package uz.gita.music_player_io.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.PlaylistData
import uz.gita.music_player_io.data.room.dao.MusicDao
import uz.gita.music_player_io.domain.PlaylistUseCase
import javax.inject.Inject

class PlaylistUseCaseImpl @Inject constructor(
    private val musicDao: MusicDao
) : PlaylistUseCase {

    override suspend fun addNewPlaylist(playlistData: PlaylistData) {
        musicDao.insertPlaylist(playlistData)
    }

    override fun getAllPlaylist(): Flow<List<PlaylistData>> = musicDao.getAllPlaylist()

}