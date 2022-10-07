package uz.gita.music_player_io.domain.impl

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.domain.GetAllMusicsUseCase
import uz.gita.music_player_io.utils.toMusicData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllMusicsUseCaseImpl @Inject constructor(
    @ApplicationContext val ctx: Context
) : GetAllMusicsUseCase {
    override suspend fun getAllMusics(): List<MusicData> {
        val musicList = ArrayList<MusicData>()

        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"

        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID
        )
        val cursor: Cursor? = ctx.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            null
        )

        for (i in 0 until cursor?.count!!) {
            cursor.moveToPosition(i)
            musicList.add(cursor.toMusicData(ctx))
        }
        return musicList
    }
}