package uz.gita.music_player_io.presentation.screens.favourite

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
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.databinding.ScreenFavouriteBinding
import uz.gita.music_player_io.presentation.screens.home.pages.songs.SongsAdapter
import uz.gita.music_player_io.presentation.viewmodels.FavouriteViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.FavouriteViewModelImpl
import uz.gita.music_player_io.utils.MusicPlaying

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:40
 */

@AndroidEntryPoint
class FavouriteScreen : Fragment(R.layout.screen_favourite){

    private val viewModel: FavouriteViewModel by viewModels<FavouriteViewModelImpl>()

    private val adapter: SongsAdapter by lazy(LazyThreadSafetyMode.NONE) { SongsAdapter() }

    private val binding: ScreenFavouriteBinding by viewBinding()

    private lateinit var list: List<MusicData>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavourite.adapter = adapter

        viewModel.getAllFavouriteFlow.onEach {
            list = it
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        adapter.setItemFavouriteClickListener {
            viewModel.updateMusic(it)
        }

        adapter.setItemClickListener {
            MusicPlaying.setMusicList(list)
            MusicPlaying.clickMusic(it)
        }

        viewModel.getAllFavouriteMusic()

    }
}