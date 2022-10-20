package uz.gita.music_player_io

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.broadcast.MusicBroadcast
import uz.gita.music_player_io.service.MusicService
import uz.gita.music_player_io.utils.MusicPlaying

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onDestroy() {
        super.onDestroy()
        MusicPlaying.mediaPlayer?.stop()
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
    }
}