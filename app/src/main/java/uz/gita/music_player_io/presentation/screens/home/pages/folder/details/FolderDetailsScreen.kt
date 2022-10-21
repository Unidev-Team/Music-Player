package uz.gita.music_player_io.presentation.screens.home.pages.folder.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.music_player_io.R
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

    private val adapter: SongsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SongsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewBinding.listArtistSongs.adapter = adapter

        viewModel.musicsByFolder.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        adapter.setItemClickListener {
            MusicPlaying.clickMusic(it)
            findNavController().navigate(FolderDetailsScreenDirections.actionFolderDetailsScreenToMusicDetailScreen())
        }
    }
}