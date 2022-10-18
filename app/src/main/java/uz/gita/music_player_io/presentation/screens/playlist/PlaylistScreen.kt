package uz.gita.music_player_io.presentation.screens.playlist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenPlaylistBinding

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:40
 */

@AndroidEntryPoint
class PlaylistScreen : Fragment(R.layout.screen_playlist) {

    private val binding: ScreenPlaylistBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            fab.setOnClickListener {
                val bottomSheet = BottomSheetDialog()
                bottomSheet.show(requireActivity().supportFragmentManager, "BOTTOM_SHEET")
            }
        }
    }
}