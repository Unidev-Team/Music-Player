package uz.gita.music_player_io.presentation.screens.home.pages.folder.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenFolderDetailsBinding
import uz.gita.music_player_io.presentation.screens.home.pages.songs.SongsAdapter

// Created by Jamshid Isoqov an 10/21/2022
@AndroidEntryPoint
class FolderDetailsScreen : Fragment(R.layout.screen_folder_details) {

    private val viewBinding: ScreenFolderDetailsBinding by viewBinding()

    private val adapter: SongsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SongsAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.listArtistSongs.adapter = adapter
    }
}