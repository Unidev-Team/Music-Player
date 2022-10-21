package uz.gita.music_player_io.presentation.screens.home.pages.folder

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.PageFolderBinding
import uz.gita.music_player_io.presentation.viewmodels.FolderViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.FolderViewModelImpl

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 12:28
 */

@AndroidEntryPoint
class FolderPage : Fragment(R.layout.page_folder) {

    private val viewModel: FolderViewModel by viewModels<FolderViewModelImpl>()
    private val binding: PageFolderBinding by viewBinding()
    private val adapter: FoldersAdapter by lazy(LazyThreadSafetyMode.NONE) { FoldersAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        setupObserver()
        itemClick()
    }

    private fun initView() {
        binding.apply {
            binding.listFolders.adapter = adapter
            binding.tcFoldersCount.text = adapter.currentList.size.toString()
        }
    }

    private fun setupObserver() {
        viewModel.getAllFolders.onEach {
            adapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun itemClick() {
        adapter.setItemClickListener {

        }
    }
}