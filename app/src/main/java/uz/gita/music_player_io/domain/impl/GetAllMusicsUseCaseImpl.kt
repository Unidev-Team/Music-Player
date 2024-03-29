package uz.gita.music_player_io.domain.impl

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.domain.GetAllMusicsUseCase
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllMusicsUseCaseImpl @Inject constructor(
    @ApplicationContext val ctx: Context
) : GetAllMusicsUseCase {

    override suspend fun getAllMusics(): List<MusicData> {
        return loadAllMusicsFromStorage()
    }

    private fun loadAllMusicsFromStorage(): ArrayList<MusicData> {
        val tempList = ArrayList<MusicData>()
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATE_ADDED,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.ALBUM_ID
        )

        val cursor = ctx.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            MediaStore.Audio.Media.DATE_ADDED + " DESC",
            null
        )

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val titleC = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))
                    val idC = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))
                    val albumC = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM))
                    val artistC = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))

                    var newStr = ""
                    println(newStr)

                    val pathC = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))

                    val packageData = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))

                    val size = packageData.split("/").size

                    packageData.split("/").subList(0, size - 1).forEach {
                        newStr += "$it/"
                    }

                    val newPackage = newStr

                    val durationC = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))
                    val albumIdC = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)).toString()

                    val uri = Uri.parse("content://media/external/audio/albumart")
                    val artUriC = Uri.withAppendedPath(uri, albumIdC).toString()
                    val music = MusicData(
                        id = idC,
                        title = titleC,
                        album = albumC,
                        artistName = artistC,
                        duration = durationC,
                        image = artUriC,
                        displayName = "",
                        path = pathC,
                        packageMusic = newPackage,
                    )
                    val file = File(music.path)
                    if (file.exists()) {
                        tempList.add(music)
                    }
                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return tempList
    }
}