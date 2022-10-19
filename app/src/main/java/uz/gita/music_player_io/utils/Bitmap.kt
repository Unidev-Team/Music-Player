package uz.gita.music_player_io.utils

import android.media.MediaMetadataRetriever

// Created by Jamshid Isoqov an 10/19/2022


fun getImageArt(path: String): ByteArray? {
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(path)
    return retriever.embeddedPicture
}