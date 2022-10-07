package uz.gita.music_player_io.domain.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.room.dao.MusicDao
import uz.gita.music_player_io.domain.GetAllMusicsUseCase
import uz.gita.music_player_io.domain.MusicsUseCase
import javax.inject.Inject

class MusicsUseCaseImpl @Inject constructor(
    private val dao: MusicDao,
    private val getAllMusicsUseCase: GetAllMusicsUseCase
) : MusicsUseCase {


    override suspend fun updateMusic(musicData: MusicData) =
        dao.updateMusic(musicData)

    override suspend fun deleteMusic(musicData: MusicData) =
        dao.deleteMusic(musicData)

    override suspend fun refreshMusics() {
        dao.clearAndUpdateData(getAllMusicsUseCase.getAllMusics())
    }

    override fun getAllMusics(): Flow<List<MusicData>> =
        dao.getAllMusics()
}