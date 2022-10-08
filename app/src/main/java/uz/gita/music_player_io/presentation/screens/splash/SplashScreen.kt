package uz.gita.music_player_io.presentation.screens.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.music_player_io.R
import uz.gita.music_player_io.presentation.viewmodels.SplashViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.SplashViewModelImpl

// Created by Jamshid Isoqov an 10/7/2022
@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    private val navController by lazy { findNavController() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.openIntroFlow.onEach {
            navController.navigate(SplashScreenDirections.actionSplashScreenToIntroScreen())
        }.launchIn(lifecycleScope)

        viewModel.openMainFlow.onEach {
            navController.navigate(SplashScreenDirections.actionSplashScreenToMainScreen())
        }.launchIn(lifecycleScope)
    }

}