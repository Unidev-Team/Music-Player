package uz.gita.music_player_io.domain.impl

import uz.gita.music_player_io.data.pref.MySharedPref
import uz.gita.music_player_io.domain.SplashUseCase
import javax.inject.Inject

class SplashUseCaseImpl @Inject constructor(
    private val mySharedPref: MySharedPref
) : SplashUseCase {
    override suspend fun getIsFirst(): Boolean {
        val isFirst = mySharedPref.isFirst
        if (isFirst) {
            mySharedPref.isFirst = false
        }
        return isFirst
    }
}