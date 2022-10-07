package uz.gita.music_player_io.data.pref

import android.content.Context
import android.content.SharedPreferences
import uz.gita.music_player_io.utils.SharedPreference
import javax.inject.Inject

// Created by Jamshid Isoqov an 10/8/2022
class MySharedPref @Inject constructor(
    context: Context,
    sharedPreferences: SharedPreferences
) : SharedPreference(context, sharedPreferences) {

    var isFirst: Boolean by Booleans(true)

}