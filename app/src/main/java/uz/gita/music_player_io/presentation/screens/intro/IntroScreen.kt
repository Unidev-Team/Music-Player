package uz.gita.music_player_io.presentation.screens.intro

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenIntroBinding
import uz.gita.music_player_io.presentation.screens.intro.adapter.IntroAdapter
import uz.gita.music_player_io.presentation.viewmodels.IntroViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.IntroViewModelImpl

// Created by Jamshid Isoqov an 10/8/2022

@AndroidEntryPoint
class IntroScreen : Fragment(R.layout.screen_intro) {
    private val binding: ScreenIntroBinding by viewBinding()
    private val adapter: IntroAdapter by lazy { IntroAdapter() }
    private var count = 0
    private val viewModel: IntroViewModel by viewModels<IntroViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPagerIntro.adapter = adapter

        binding.btnNextIntro.setOnClickListener {
            count++
            if (count == 2) {
                binding.btnNextIntro.text = getString(R.string.get_started)
            }
            if (count == 3) {
                findNavController().navigate(IntroScreenDirections.actionIntroScreenToMainScreen())
            } else {
                binding.viewPagerIntro.setCurrentItem(count, true)
            }
        }
        binding.viewPagerIntro.isUserInputEnabled = false

        binding.dotIndicatorIntro.attachTo(binding.viewPagerIntro)
    }
}