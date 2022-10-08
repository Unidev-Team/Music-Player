package uz.gita.music_player_io.presentation.screens.home.pages.songs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.pref.MySharedPref
import uz.gita.music_player_io.databinding.PageSongsBinding
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.HomeViewModelImpl
import javax.inject.Inject

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 12:28
 */

@AndroidEntryPoint
class SongsPage : Fragment(R.layout.page_songs) {

    private val binding: PageSongsBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter by lazy { SongsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSongs.adapter = adapter
        viewModel.getAllMusics()
        viewModel.getAllMusics().onEach {
            adapter.submitList(it)
        }.launchIn(lifecycleScope)
    }
}