package uz.gita.music_player_io.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import uz.gita.music_player_io.service.State
import uz.gita.music_player_io.utils.MusicPlaying

// Created by Jamshid Isoqov an 10/19/2022
class MusicBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        when (intent?.action) {
            State.PLAY.name -> {
                if (MusicPlaying.mediaPlayer?.isPlaying!!) {
                    MusicPlaying.pauseMusic()
                } else {
                    MusicPlaying.startMusic()
                }
            }
            State.NEXT.name -> {
                MusicPlaying.clickMusic(MusicPlaying.positionMusic + 1)
            }
            State.PREVIOUS.name -> {
                MusicPlaying.clickMusic(MusicPlaying.positionMusic - 1)
            }
        }

    }

}