package uz.gita.music_player_io.presentation.screens.home.pages.artists.detail

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
import uz.gita.music_player_io.databinding.ScreenArtistDetialBinding
import uz.gita.music_player_io.presentation.screens.home.pages.songs.SongsAdapter
import uz.gita.music_player_io.presentation.viewmodels.ArtistSongsViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.ArtistSongsViewModelImpl
import uz.gita.music_player_io.utils.MusicPlaying

@AndroidEntryPoint
class ArtistDetailScreen : Fragment(R.layout.screen_artist_detial) {

    private var isPlaying = false
    private val args: ArtistDetailScreenArgs by navArgs()
    private val viewModel: ArtistSongsViewModel by viewModels<ArtistSongsViewModelImpl>()
    private val adapter by lazy { SongsAdapter() }
    private val binding: ScreenArtistDetialBinding by viewBinding()
    private var list:List<MusicData> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSongsByArtist(args.artistData.artistName)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listArtistSongs.adapter = adapter

        viewModel.songsByArtisFlow.onEach {
            adapter.submitList(it)
            list = it
        }.launchIn(lifecycleScope)

        adapter.setItemClickListener {
            MusicPlaying.clickMusic(it)
            MusicPlaying.setMusicList(list)

        }
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
            imgAlbum.setImageURI(Uri.parse(it.image))
            tvSingerSong.text = "${it.artistName} - ${it.title}"
            isPlaying = true
        }
    }
}