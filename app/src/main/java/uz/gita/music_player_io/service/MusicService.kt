package uz.gita.music_player_io.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import uz.gita.music_player_io.R
import uz.gita.music_player_io.broadcast.MusicBroadcast
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.utils.MusicPlaying
import uz.gita.music_player_io.utils.getImageArt
import kotlin.math.absoluteValue

// Created by Jamshid Isoqov an 10/19/2022
class MusicService : Service() {
    private lateinit var mediaSession: MediaSessionCompat


    override fun onBind(intent: Intent?): IBinder? {
        mediaSession = MediaSessionCompat(baseContext, "My Music")
        return MyBinder()
    }

    override fun onCreate() {
        super.onCreate()
        createNotification()
    }


    inner class MyBinder : Binder() {
        fun currentService(): MusicService {
            return this@MusicService
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        createNotification()

        return START_REDELIVER_INTENT
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun createNotification() {
        val music = MusicPlaying.musicLiveData.value

        /*val imgArt = getImageArt(music.image)
        val image = if (imgArt != null) {
            BitmapFactory.decodeByteArray(imgArt, 0, imgArt.size)
        } else {
            BitmapFactory.decodeResource(resources, R.drawable.ic_music)
        }*/

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

        val prevIntent =
            Intent(baseContext, MusicBroadcast::class.java).setAction(State.PREVIOUS.name)
        val prevPendingIntent = PendingIntent.getBroadcast(
            baseContext,
            0,
            prevIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val playIntent = Intent(baseContext, MusicBroadcast::class.java).setAction(State.PLAY.name)
        val playPendingIntent = PendingIntent.getBroadcast(
            baseContext,
            0,
            playIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val nextIntent = Intent(baseContext, MusicBroadcast::class.java).setAction(State.NEXT.name)
        val nextPendingIntent = PendingIntent.getBroadcast(
            baseContext,
            0,
            nextIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(baseContext, CHANNEL_ID)
            .setContentTitle(music?.title)
            .setContentText(music?.artistName)
            .setSmallIcon(R.drawable.ic_music)
            //.setLargeIcon(image)
            /*.setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setMediaSession(mediaSession.sessionToken)
            )*/
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOnlyAlertOnce(true)
            .addAction(R.drawable.ic_previous_left, "Previous", prevPendingIntent)
            .addAction(R.drawable.play, "Play", playPendingIntent)
            .addAction(R.drawable.ic_next, "Next", nextPendingIntent)
            .build()

        startForeground(12, notification)
    }

    companion object {
        const val CHANNEL_ID = "notify"
    }
}

enum class State {
    PREVIOUS, PLAY, NEXT
}