package uz.gita.music_player_io.presentation.screens.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenMainBinding
import uz.gita.music_player_io.presentation.viewmodels.HomeViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.HomeViewModelImpl

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 01:35
 */

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {

    private val binding: ScreenMainBinding by viewBinding()
    private lateinit var navController: NavController
    private val viewModel: HomeViewModel by viewModels<HomeViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestRuntimePermission()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.bnv.setOnItemReselectedListener {
            navController = Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
            binding.bnv.setupWithNavController(navController)
        }
    }

    private fun requestRuntimePermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 13
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 13) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.refreshAllMusics()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 13
                )
            }
        }
    }
}