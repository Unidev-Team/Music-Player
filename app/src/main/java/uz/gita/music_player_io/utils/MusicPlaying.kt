package uz.gita.music_player_io.utils

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import timber.log.Timber
import uz.gita.music_player_io.App
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.service.MusicService

object MusicPlaying {

    var mediaPlayer: MediaPlayer? = null

    val musicLiveData = MutableLiveData<MusicData>()

    var positionMusic = -1

    var listMusics: List<MusicData> = emptyList()


    fun clickMusic(pos: Int) {

        positionMusic = if (pos == listMusics.size) {
            0
        } else {
            pos
        }

        musicLiveData.value = listMusics[positionMusic]

        if (mediaPlayer != null && mediaPlayer?.isPlaying!!) {
            mediaPlayer?.stop()
        }

        val intent = Intent(App.context, MusicService::class.java)
        ContextCompat.startForegroundService(App.context, intent)

        mediaPlayer = MediaPlayer.create(App.context, Uri.parse(listMusics[positionMusic].path))
        startMusic()

    }

    fun setMusicList(listMusics: List<MusicData>) {
        this.listMusics = listMusics
    }

    fun startMusic() {
        mediaPlayer?.start()
    }

    fun pauseMusic() {
        mediaPlayer?.pause()
    }
}