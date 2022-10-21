package uz.gita.music_player_io.domain.impl

import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.room.dao.MusicDao
import uz.gita.music_player_io.domain.FolderUseCase
import javax.inject.Inject


class FolderUseCaseImpl @Inject constructor(
    private val dao: MusicDao
) : FolderUseCase {
    override suspend fun getFolders(): List<FolderData> = dao.getFolders()

    override suspend fun getAllMusicsByFolder(path: String): List<MusicData> =
        dao.getFolderMusicByName(path)
}