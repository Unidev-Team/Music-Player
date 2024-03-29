package uz.gita.music_player_io.presentation.screens.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.databinding.ScreenMainBinding
import uz.gita.music_player_io.utils.MusicPlaying

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:35
 */

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding: ScreenMainBinding by viewBinding()
    private lateinit var navController: NavController
    private var isPlaying = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController =
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
        binding.bnv.setupWithNavController(navController)

        binding.iconPlayOrPause.setOnClickListener {
            if (isPlaying) {
                MusicPlaying.pauseMusic()
                binding.iconPlayOrPause.setImageResource(R.drawable.play)
            } else {
                MusicPlaying.startMusic()
                binding.iconPlayOrPause.setImageResource(R.drawable.ic_pause)
            }
            isPlaying = !isPlaying
        }

        MusicPlaying.playingObserver.observe(viewLifecycleOwner) {
            if (it) {
                binding.iconPlayOrPause.setImageResource(R.drawable.ic_pause)
            } else {
                binding.iconPlayOrPause.setImageResource(R.drawable.play)
            }
        }

        binding.iconNextSong.setOnClickListener {
            MusicPlaying.clickMusic(MusicPlaying.positionMusic + 1)
        }

        binding.bottomMusicContainer.setOnClickListener {
            findNavController().navigate(MainScreenDirections.actionMainScreenToMusicDetailScreen())
        }

        MusicPlaying.musicLiveData.observe(viewLifecycleOwner, musicObserver)

    }

    @SuppressLint("SetTextI18n")
    private val musicObserver = Observer<MusicData> {

        if (!binding.bottomMusicContainer.isVisible) {
            binding.bottomMusicContainer.visibility = View.VISIBLE
        }

        binding.apply {
            Glide.with(requireContext())
                .load(it.image)
                .placeholder(R.drawable.ic_music)
                .into(imgAlbum)
            tvSingerSong.text = "${it.artistName} - ${it.title}"
            tvSingerSong.maxLines = 2
            isPlaying = true
        }
    }
}