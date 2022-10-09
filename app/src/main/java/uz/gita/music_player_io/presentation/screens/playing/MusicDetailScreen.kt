package uz.gita.music_player_io.presentation.screens.playing

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenMusicDetailBinding
import uz.gita.music_player_io.presentation.viewmodels.MusicDetailViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.MusicDetailViewModelImpl
import uz.gita.music_player_io.utils.MusicPlaying

@AndroidEntryPoint
class MusicDetailScreen: Fragment(R.layout.screen_music_detail) {

    private val viewModel: MusicDetailViewModel by viewModels<MusicDetailViewModelImpl>()

    private val binding: ScreenMusicDetailBinding by viewBinding(ScreenMusicDetailBinding::bind)

    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.clickBack.observe(this, clickBackObserver)

        viewModel.clickPrevious.observe(this, clickPreviousObserver)

        viewModel.clickNextSong.observe(this, clickNextSongObserver)

        viewModel.clickStopOrStart.observe(this, clickStopOrStartObserver)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        changeUI(MusicPlaying.positionMusic)

        isPlaying = true

        binding.apply {
            iconBack.setOnClickListener {
                viewModel.setClickBack()
            }

            iconPreviousSong.setOnClickListener {
                viewModel.setClickPrevious()
            }

            iconStopOrPlay.setOnClickListener {
                viewModel.setClickStopOrStart()
            }

            iconNextSong.setOnClickListener {
                viewModel.setClickNextSong()
            }
        }
    }

    private val clickBackObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val clickPreviousObserver = Observer<Unit> {
        MusicPlaying.clickMusic(MusicPlaying.positionMusic - 1)
        changeUI(MusicPlaying.positionMusic)
    }

    private val clickNextSongObserver = Observer<Unit> {
        MusicPlaying.clickMusic(MusicPlaying.positionMusic + 1)
        changeUI(MusicPlaying.positionMusic)
    }

    private val clickStopOrStartObserver = Observer<Unit> {
        MusicPlaying.clickMusic(MusicPlaying.positionMusic)
        if (isPlaying) {
            MusicPlaying.pauseMusic()
            binding.iconStopOrPlay.setImageResource(R.drawable.play)
        } else {
            MusicPlaying.startMusic()
            binding.iconStopOrPlay.setImageResource(R.drawable.ic_pause)
        }
        isPlaying = !isPlaying
    }

    private fun changeUI(pos: Int) {
        binding.apply {
            tvSinger.text = MusicPlaying.listMusics[pos].artistName
            tvSong.text = MusicPlaying.listMusics[pos].title
            imgAlbum.setImageURI(Uri.parse(MusicPlaying.listMusics[pos].album))
        }
    }
}