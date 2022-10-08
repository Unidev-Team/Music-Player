package uz.gita.music_player_io.presentation.screens.main

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.databinding.ScreenMainBinding
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.HomeViewModelImpl
import uz.gita.music_player_io.utils.MusicPlaying

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:35
 */

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding: ScreenMainBinding by viewBinding()
    private lateinit var navController: NavController
    private var mediaPlayer: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bnv.setOnItemReselectedListener {
            navController =
                Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
            binding.bnv.setupWithNavController(navController)
        }

        MusicPlaying.musicLiveData.observe(viewLifecycleOwner, musicObserver)

    }

    @SuppressLint("SetTextI18n")
    private val musicObserver = Observer<MusicData> {

        if(!binding.bottomMusicContainer.isVisible) {
            binding.bottomMusicContainer.visibility = View.VISIBLE
        }

        if(mediaPlayer != null && mediaPlayer?.isPlaying!!) {
            mediaPlayer?.stop()
        }

        binding.apply {
            mediaPlayer = MediaPlayer.create(requireContext(), Uri.parse(it.path))
            imgAlbum.setImageURI(Uri.parse(it.image))
            tvSingerSong.text = "${it.artistName} - ${it.title}"
            mediaPlayer?.start()
        }
    }
}