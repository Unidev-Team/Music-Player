package uz.gita.music_player_io.presentation.screens.playlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.PlaylistData
import uz.gita.music_player_io.databinding.BottomSheetBinding
import uz.gita.music_player_io.presentation.viewmodels.PlayListViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.PlayListViewModelImpl

/**
 *  Created by Nurlibay Koshkinbaev on 18/10/2022 23:40
 */

@AndroidEntryPoint
class BottomSheetDialog : BottomSheetDialogFragment() {

    private val binding: BottomSheetBinding by viewBinding()
    private val viewModel: PlayListViewModel by viewModels<PlayListViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnCancel.setOnClickListener {
                dismiss()
            }

            btnCreate.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.addNewPlaylist(PlaylistData(0, etPlaylistName.text.toString()))
                    dismiss()
                }
            }
        }
    }
}