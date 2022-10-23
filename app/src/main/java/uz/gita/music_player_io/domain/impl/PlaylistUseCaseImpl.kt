package uz.gita.music_player_io.domain.impl

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import uz.gita.music_player_io.data.model.PlaylistData
import uz.gita.music_player_io.data.model.data.PlayListWithMusics
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

    override fun getPlaylistMusic(id: Int): Flow<PlaylistData> = musicDao.getPlaylistById(id)

    override fun getMusicsWithPlaylist(id: Int): Flow<List<PlayListWithMusics>> = callbackFlow {
        musicDao.getAllMusics().combine(musicDao.getPlaylistById(id)) { f1, f2 ->
            val playListWithMusics = ArrayList<PlayListWithMusics>()
            val playlist = f2.musicList
            var isInPlaylistData: Boolean
            for (i in f1) {
                isInPlaylistData = playlist.any {
                    it.title == i.title&&it.path==i.path
                }
                playListWithMusics.add(PlayListWithMusics(i, isInPlaylistData))
            }
            playListWithMusics

        }.collectLatest {
            trySend(it)
        }
        awaitClose {}
    }

    override suspend fun changePlayList(playlistData: PlaylistData) {
        musicDao.updatePlayList(playlistData)
    }
}