package uz.gita.music_player_io.presentation.screens.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenMainBinding

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:35
 */

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private lateinit var navController: NavController
    private lateinit var binding: ScreenMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ScreenMainBinding.bind(view)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
        binding.bnv.setupWithNavController(navController)
    }
}