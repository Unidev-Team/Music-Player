package uz.gita.music_player_io.data.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.music_player_io.data.model.ArtistData
import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.model.PlaylistData

// Created by Jamshid Isoqov an 10/7/2022

@Dao
interface MusicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(musicData: MusicData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(musicList: List<MusicData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlistName: PlaylistData)

    @Update
    suspend fun updateMusic(musicData: MusicData)

    @Delete
    suspend fun deleteMusic(musicData: MusicData)

    @Query("SELECT*FROM musics")
    fun getAllMusics(): Flow<List<MusicData>>

    @Query("DELETE FROM musics")
    suspend fun deleteAllMusics()

    @Transaction
    suspend fun clearAndUpdateData(musicList: List<MusicData>) {
        deleteAllMusics()
        insertMusic(musicList)
    }

    @Query("SELECT artistName,image,count(id) as count FROM musics group by artistName")
    suspend fun getAllArtist(): List<ArtistData>

    @Query("SELECT * FROM musics WHERE artistName = :artistName")
    suspend fun getAllSongsByArtist(artistName: String): List<MusicData>

    @Query("SELECT * FROM playlistdata")
    fun getAllPlaylist(): Flow<List<PlaylistData>>

    @Query("SELECT * FROM playlistdata WHERE id=:id")
    fun getPlaylistById(id: Int): Flow<PlaylistData>


    @Query("SELECT * FROM playlistdata WHERE id=:id")
    suspend fun getPlayListById(id: Int): PlaylistData

    @Query("select album,image,count(id),path as count from musics group by path")
    suspend fun getFolders(): List<FolderData>

    @Query("select * from musics where path=:path ")
    suspend fun getFolderMusicByName(path: String): List<MusicData>


    @Update
    fun updatePlayList(playlistData: PlaylistData)

}