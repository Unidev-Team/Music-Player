package uz.gita.music_player_io.presentation.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenHomeBinding
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.HomeViewModelImpl

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:33
 */

@AndroidEntryPoint
class HomeScreen: Fragment(R.layout.screen_home) {

    private val binding: ScreenHomeBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    override fun onResume() {
        super.onResume()
        viewModel.getAllMusics()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}