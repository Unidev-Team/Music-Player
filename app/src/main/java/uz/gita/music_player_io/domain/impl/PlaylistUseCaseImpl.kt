package uz.gita.music_player_io.domain.impl

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import uz.gita.music_player_io.data.model.MusicData
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
            var counter = 0
            val playlist = f2.musicList
            var isInPlaylistData: Boolean
            var music: MusicData = f1[0]
            for (i in f1) {
                isInPlaylistData = if (counter < playlist.size && i.title == music.title) {
                    counter++
                    if (counter < playlist.size)
                        music = playlist[counter]
                    true
                } else false
                playListWithMusics.add(PlayListWithMusics(i, isInPlaylistData))
            }
            playListWithMusics

        }.collectLatest {
            trySend(it)
        }
        awaitClose{}
    }

    override suspend fun changePlayList(playlistData: PlaylistData) {
        musicDao.updatePlayList(playlistData)
    }
}