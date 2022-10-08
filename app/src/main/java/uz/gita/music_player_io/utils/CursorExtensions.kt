package uz.gita.music_player_io.utils

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.provider.MediaStore
import uz.gita.music_player_io.data.model.MusicData

// Created by Jamshid Isoqov an 9/22/2022

/*fun Cursor.toMusicData(ctx: Context): MusicData {

    val id = this.getString(this.getColumnIndexOrThrow(MediaStore.Audio.Media._ID))

    val artist = this.getString(this.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST))

    val album = this.getString(this.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM))

    val title: String = this.getString(this.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))

    val displayName: String =
        this.getString(this.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME))

    val duration: Int = this.getInt(this.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION))

    val data = this.getString(this.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA))

    val albumID = this.getLong(this.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))

    return MusicData(
        "",
        artist,
        album,
        title,
        displayName,
        1,
        data,
        ctx.songArt(albumID)?.toString() ?: "empty"
    )
}*/

fun Context.songArt(albumId: Long): Uri? {
    try {
        val sArtworkUri: Uri = Uri
            .parse("content://media/external/audio/albumart")
        val uri = ContentUris.withAppendedId(sArtworkUri, albumId)
        val pfd: ParcelFileDescriptor? = this.contentResolver
            .openFileDescriptor(uri, "r")
        if (pfd != null) {
            return uri
        }
    } catch (e: Exception) {

    }
    return null
}


