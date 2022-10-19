package uz.gita.music_player_io.presentation.screens.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenHomeBinding
import uz.gita.music_player_io.presentation.screens.home.pages.HomeViewPagerAdapter
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.HomeViewModelImpl

/*
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:33
 */

@AndroidEntryPoint
class HomeScreen : Fragment(R.layout.screen_home) {

    private val binding: ScreenHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pagerMain.adapter = HomeViewPagerAdapter(requireActivity())
        val list = listOf("Songs", "Artists", "Albums")
        TabLayoutMediator(binding.tabMain, binding.pagerMain) { tab, pos ->
            tab.text = list[pos]
        }.attach()
    }
}