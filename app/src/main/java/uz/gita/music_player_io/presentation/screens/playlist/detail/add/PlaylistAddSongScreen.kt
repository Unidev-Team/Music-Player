package uz.gita.music_player_io.presentation.screens.playlist.detail.add

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenPlaylistAddSongBinding
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.HomeViewModelImpl

@AndroidEntryPoint
class PlaylistAddSongScreen : Fragment(R.layout.screen_playlist_add_song) {

    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val binding: ScreenPlaylistAddSongBinding by viewBinding()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { PlaylistAddSongAdapter() }

    private val args: PlaylistAddSongScreenArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.setPlaylistId(args.playlistData.id)

        binding.apply {
            rvSongsList.adapter = adapter

            adapter.setIconClickListener {
                viewModel.changeMusic(it)
            }
        }
        viewModel.getAllMusics().onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}