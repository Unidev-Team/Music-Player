package uz.gita.music_player_io.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

// Created by Jamshid Isoqov an 10/21/2022

@Parcelize
data class FolderData(
    val path: String,
    val count: Int
) : Parcelable