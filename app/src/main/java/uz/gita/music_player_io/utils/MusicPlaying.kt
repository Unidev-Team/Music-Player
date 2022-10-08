package uz.gita.music_player_io.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.gita.music_player_io.data.model.MusicData

object MusicPlaying {

    val musicLiveData = MutableLiveData<MusicData>()

    var positionMusic = -1

    var listMusics: List<MusicData> = emptyList()

    fun clickMusic(pos: Int) {
        positionMusic = pos
        musicLiveData.value = listMusics[positionMusic]
    }

    fun setMusicList(listMusics: List<MusicData>) {
        this.listMusics = listMusics
    }
}