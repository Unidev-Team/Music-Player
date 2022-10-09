package uz.gita.music_player_io.presentation.viewmodels

import androidx.lifecycle.LiveData

interface MusicDetailViewModel {

    val clickBack: LiveData<Unit>

    val clickPrevious: LiveData<Unit>

    val clickNextSong: LiveData<Unit>

    val clickStopOrStart: LiveData<Unit>

    fun setClickBack()

    fun setClickPrevious()

    fun setClickNextSong()

    fun setClickStopOrStart()

}