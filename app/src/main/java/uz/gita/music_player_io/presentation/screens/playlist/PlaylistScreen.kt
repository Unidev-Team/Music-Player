package uz.gita.music_player_io.presentation.screens.playlist

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
import uz.gita.music_player_io.databinding.ScreenPlaylistBinding
import uz.gita.music_player_io.presentation.viewmodels.PlayListViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.PlayListViewModelImpl

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:40
 */

@AndroidEntryPoint
class PlaylistScreen : Fragment(R.layout.screen_playlist) {

    private val binding: ScreenPlaylistBinding by viewBinding()
    private val adapter: PlaylistAdapter by lazy { PlaylistAdapter() }
    private val viewModel: PlayListViewModel by viewModels<PlayListViewModelImpl>()
    private var playlistCount = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            fab.setOnClickListener {
                val bottomSheet = BottomSheetDialog()
                bottomSheet.show(requireActivity().supportFragmentManager, "BOTTOM_SHEET")
            }
        }

        binding.rvPlaylists.adapter = adapter

        viewModel.getAllPlaylist().onEach {
            adapter.submitList(it)

            binding.tvPlaylistCount.text = it.size.toString()

        }.launchIn(viewLifecycleOwner.lifecycleScope)


        adapter.setItemClickListener {
            findNavController().navigate(
                PlaylistScreenDirections.actionPlaylistScreenToPlaylistDetailScreen(
                    it
                )
            )
        }
    }
}