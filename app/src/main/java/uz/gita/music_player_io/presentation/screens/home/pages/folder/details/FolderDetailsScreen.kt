package uz.gita.music_player_io.presentation.screens.home.pages.folder.details

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
import uz.gita.music_player_io.databinding.ScreenFolderDetailsBinding
import uz.gita.music_player_io.presentation.screens.home.pages.songs.SongsAdapter
import uz.gita.music_player_io.presentation.viewmodels.FolderDetailsViewModel
import uz.gita.music_player_io.presentation.viewmodels.FolderViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.FolderDetailsViewModelImpl
import uz.gita.music_player_io.presentation.viewmodels.impl.FolderViewModelImpl
import uz.gita.music_player_io.utils.MusicPlaying

// Created by Jamshid Isoqov an 10/21/2022
@AndroidEntryPoint
class FolderDetailsScreen : Fragment(R.layout.screen_folder_details) {

    private val viewBinding: ScreenFolderDetailsBinding by viewBinding()

    private val viewModel: FolderDetailsViewModel by viewModels<FolderDetailsViewModelImpl>()

    private val saveArgs: FolderDetailsScreenArgs by navArgs()

    private var list:List<MusicData> = listOf()

    private var isPlaying = false

    private val adapter: SongsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SongsAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listArtistSongs.adapter = adapter

        viewModel.getAllMusicsByFolder(saveArgs.folder)

        viewModel.musicsByFolder.onEach {
            adapter.submitList(it)
            list = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            MusicPlaying.setMusicList(list)
            MusicPlaying.clickMusic(it)
        }

        viewBinding.bottomFolderContainer.setOnClickListener {
            findNavController().navigate(FolderDetailsScreenDirections.actionFolderDetailsScreenToMusicDetailScreen())
        }

        viewBinding.iconPlayOrPause.setOnClickListener {
            if (isPlaying) {
                MusicPlaying.pauseMusic()
                viewBinding.iconPlayOrPause.setImageResource(R.drawable.ic_pause)
            } else {
                MusicPlaying.startMusic()
                viewBinding.iconPlayOrPause.setImageResource(R.drawable.play)
            }
            isPlaying = !isPlaying
        }

        viewBinding.iconNextSong.setOnClickListener {
            MusicPlaying.clickMusic(MusicPlaying.positionMusic+1)
        }

        MusicPlaying.musicLiveData.observe(viewLifecycleOwner, musicObserver)

    }

    @SuppressLint("SetTextI18n")
    private val musicObserver = Observer<MusicData> {

        if (!viewBinding.bottomMusicContainer.isVisible) {
            viewBinding.bottomMusicContainer.visibility = View.VISIBLE
        }

        viewBinding.apply {
            imgArtistFolder.setImageURI(Uri.parse(it.image))
            tvSingerFolder.text = "${it.artistName} - ${it.title}"
            isPlaying = true
        }
    }
}