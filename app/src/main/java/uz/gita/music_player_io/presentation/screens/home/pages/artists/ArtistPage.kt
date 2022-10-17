package uz.gita.music_player_io.presentation.screens.home.pages.artists

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.PageArtistsBinding
import uz.gita.music_player_io.presentation.viewmodels.ArtistPageViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.ArtistPageViewModelImpl

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 12:28
 */
@AndroidEntryPoint
class ArtistPage: Fragment(R.layout.page_artists) {

    private val adapter by lazy { ArtistAdapter() }
    private val binding: PageArtistsBinding by viewBinding()
    private val viewModel: ArtistPageViewModel by viewModels<ArtistPageViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.artistList.adapter = adapter

        viewModel.artistFlow.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }
}