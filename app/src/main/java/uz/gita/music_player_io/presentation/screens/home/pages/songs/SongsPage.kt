package uz.gita.music_player_io.presentation.screens.home.pages.songs

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.PageSongsBinding
import uz.gita.music_player_io.presentation.screens.main.MainScreenDirections
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.HomeViewModelImpl
import uz.gita.music_player_io.service.MusicService
import uz.gita.music_player_io.utils.MusicPlaying

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 12:28
 */

@AndroidEntryPoint
class SongsPage : Fragment(R.layout.page_songs),ServiceConnection {

    private val binding: PageSongsBinding by viewBinding()
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()
    private val adapter by lazy { SongsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSongs.adapter = adapter
        viewModel.getAllMusics()
        viewModel.getAllMusicsPlaylist().onEach {
            MusicPlaying.setMusicList(it)
            adapter.submitList(it)
        }.launchIn(lifecycleScope)

        adapter.setItemClickListener {
            MusicPlaying.clickMusic(it)
            val intent = Intent(requireContext(), MusicService::class.java)
            intent.putExtra("data", MusicPlaying.listMusics[it])
            ContextCompat.startForegroundService(requireContext(), intent)
            findNavController().navigate(MainScreenDirections.actionMainScreenToMusicDetailScreen())
        }
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MusicService.MyBinder

    }

    override fun onServiceDisconnected(name: ComponentName?) {

    }
}