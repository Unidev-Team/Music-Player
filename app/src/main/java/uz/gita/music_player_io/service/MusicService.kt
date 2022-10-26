package uz.gita.music_player_io.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.app.NotificationCompat
import uz.gita.music_player_io.R
import uz.gita.music_player_io.broadcast.MusicBroadcast
import uz.gita.music_player_io.utils.MusicPlaying

// Created by Jamshid Isoqov an 10/19/2022
class MusicService : Service() {

    private lateinit var mediaSession: MediaSessionCompat

    private var musicBroadcast: MusicBroadcast? = null

    private var stateMus = 0

    override fun onBind(intent: Intent?): IBinder? {
        mediaSession = MediaSessionCompat(baseContext, "My Music")
        return MyBinder()
    }

    override fun onCreate() {
        super.onCreate()
        if (musicBroadcast==null){
            musicBroadcast = MusicBroadcast()
        }

        registerReceiver(musicBroadcast, IntentFilter().apply {
            addAction(State.NEXT.name)
            addAction(State.PLAY.name)
            addAction(State.PREVIOUS.name)
        })

        createNotification()
    }

    inner class MyBinder : Binder() {
        fun currentService(): MusicService {
            return this@MusicService
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        stateMus = intent?.getIntExtra("stateMusic", 0)!!
        showNotification(stateMus)
        return START_REDELIVER_INTENT
    }

    private fun createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                "Now playing song",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "This is a important channel for showing song!!"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun showNotification(state: Int) {
        val music = MusicPlaying.musicLiveData.value

        /*val imgArt = getImageArt(music.image)
        val image = if (imgArt != null) {
            BitmapFactory.decodeByteArray(imgArt, 0, imgArt.size)
        } else {
            BitmapFactory.decodeResource(resources, R.drawable.ic_music)
        }*/

        val prevIntent =
            Intent(baseContext,musicBroadcast!!.javaClass).setAction(State.PREVIOUS.name)
        val prevPendingIntent = PendingIntent.getBroadcast(
            baseContext,
            0,
            prevIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val playIntent = Intent(baseContext, musicBroadcast!!.javaClass).setAction(State.PLAY.name)
        val playPendingIntent = PendingIntent.getBroadcast(
            baseContext,
            0,
            playIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val nextIntent = Intent(baseContext, musicBroadcast!!.javaClass).setAction(State.NEXT.name)
        val nextPendingIntent = PendingIntent.getBroadcast(
            baseContext,
            0,
            nextIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(baseContext, CHANNEL_ID)
            .setContentTitle(music?.title)
            .setContentText(music?.artistName)
            .setSmallIcon(R.drawable.ic_music)
            //.setLargeIcon(image)
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.ic_previous_left, "Previous", prevPendingIntent)
            .addAction(state, "Play", playPendingIntent)
            .addAction(R.drawable.ic_next, "Next", nextPendingIntent)
            .build()

        startForeground(12, notification)
    }

    companion object {
        const val CHANNEL_ID = "notify"
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("SSS","SERVICE DESTROY" )
        unregisterReceiver(musicBroadcast)
    }
}

enum class State {
    PREVIOUS, PLAY, NEXT
}