package uz.gita.music_player_io.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.music_player_io.data.pref.MySharedPref
import uz.gita.music_player_io.data.room.AppDatabase
import uz.gita.music_player_io.data.room.dao.MusicDao
import javax.inject.Singleton

// Created by Jamshid Isoqov an 10/7/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @[Provides Singleton]
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "music_player.db")
            .build()

    @[Provides Singleton]
    fun provideMusicDao(appDatabase: AppDatabase): MusicDao = appDatabase.musicDao()

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("app_data", Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideSharedPrefs(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences
    ): MySharedPref =
        MySharedPref(context, sharedPreferences)
}