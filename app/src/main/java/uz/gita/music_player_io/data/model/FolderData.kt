package uz.gita.music_player_io.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Created by Jamshid Isoqov an 10/21/2022

@Parcelize
data class FolderData(
    val folderName: String,
    val image: String,
    val count: Int,
    val path: String
) : Parcelable
