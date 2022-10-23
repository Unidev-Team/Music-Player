package uz.gita.music_player_io.presentation.screens.playlist.detail

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.databinding.ScreenPlaylistDetailBinding
import uz.gita.music_player_io.presentation.screens.home.pages.songs.SongsAdapter
import uz.gita.music_player_io.presentation.viewmodels.PlaylistDetailViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.PlaylistDetailViewModelImpl
import uz.gita.music_player_io.utils.MusicPlaying

@AndroidEntryPoint
class PlaylistDetailScreen : Fragment(R.layout.screen_playlist_detail) {

    private val viewModel: PlaylistDetailViewModel by viewModels<PlaylistDetailViewModelImpl>()
    private var isPlaying = false
    private val saveArgs: PlaylistDetailScreenArgs by navArgs()
    private val musicAdapter: SongsAdapter by lazy { SongsAdapter() }
    private val binding: ScreenPlaylistDetailBinding by viewBinding(ScreenPlaylistDetailBinding::bind)
    private val navController by lazy { findNavController() }
    private lateinit var musicList:List<MusicData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listPlaylistSongs.adapter = musicAdapter

        binding.tvPlaylistName.text = saveArgs.playlistData.name

        binding.btnAdd.setOnClickListener {
            navController.navigate(PlaylistDetailScreenDirections.actionPlaylistDetailScreenToPlaylistAddSongScreen(saveArgs.playlistData))
        }

        viewModel.playlistSongFlow.onEach {
            musicList = it.musicList
            musicAdapter.submitList(it.musicList)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        musicAdapter.setItemClickListener {
            MusicPlaying.setMusicList(musicList)
            MusicPlaying.clickMusic(it)
        }
        
        viewModel.getAllPlaylistSong(saveArgs.playlistData.id)

        binding.iconPlayOrPause.setOnClickListener {
            if (isPlaying) {
                MusicPlaying.pauseMusic()
                binding.iconPlayOrPause.setImageResource(R.drawable.ic_pause)
            } else {
                MusicPlaying.startMusic()
                binding.iconPlayOrPause.setImageResource(R.drawable.play)
            }
            isPlaying = !isPlaying
        }

        binding.iconNextSong.setOnClickListener {
            MusicPlaying.clickMusic(MusicPlaying.positionMusic+1)
        }

        MusicPlaying.musicLiveData.observe(viewLifecycleOwner, musicObserver)

    }

    @SuppressLint("SetTextI18n")
    private val musicObserver = Observer<MusicData> {

        if (!binding.bottomMusicContainer.isVisible) {
            binding.bottomMusicContainer.visibility = View.VISIBLE
        }

        binding.apply {
            imgArtist.setImageURI(Uri.parse(it.image))
            tvSingerSong.text = "${it.artistName} - ${it.title}"
            isPlaying = true
        }
    }
}