package uz.gita.music_player_io.presentation.screens.playing

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ScreenMusicDetailBinding
import uz.gita.music_player_io.presentation.viewmodels.MusicDetailViewModel
import uz.gita.music_player_io.presentation.viewmodels.impl.MusicDetailViewModelImpl
import uz.gita.music_player_io.utils.MusicPlaying
import uz.gita.music_player_io.utils.extensions.formatDuration

@AndroidEntryPoint
class MusicDetailScreen : Fragment(R.layout.screen_music_detail) {

    private val viewModel: MusicDetailViewModel by viewModels<MusicDetailViewModelImpl>()

    private val binding: ScreenMusicDetailBinding by viewBinding()

    private var isPlaying = false

    private var job: Job? = null

    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.clickBack.observe(this, clickBackObserver)

        viewModel.clickPrevious.observe(this, clickPreviousObserver)

        viewModel.clickNextSong.observe(this, clickNextSongObserver)

        viewModel.clickStopOrStart.observe(this, clickStopOrStartObserver)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        isPlaying = true
        binding.iconStopOrPlay.setImageResource(R.drawable.ic_pause)

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

        MusicPlaying.mutableMusicPosition.observe(viewLifecycleOwner) {
            changeUI(it)
        }

        MusicPlaying.playingObserver.observe(viewLifecycleOwner) {
            if (it) {
                binding.iconStopOrPlay.setImageResource(R.drawable.ic_pause)
            } else {
                binding.iconStopOrPlay.setImageResource(R.drawable.play)
            }
        }
    }

    private val clickBackObserver = Observer<Unit> {
        findNavController().navigateUp()
    }

    private val clickPreviousObserver = Observer<Unit> {
        if (MusicPlaying.positionMusic > 0) {
            MusicPlaying.clickMusic(MusicPlaying.positionMusic - 1)
            binding.iconStopOrPlay.setImageResource(R.drawable.ic_pause)
        } else {
            Toast.makeText(requireContext(), "First Song!", Toast.LENGTH_SHORT).show()
        }
    }

    private val clickNextSongObserver = Observer<Unit> {
        MusicPlaying.clickMusic(MusicPlaying.positionMusic + 1)
        binding.iconStopOrPlay.setImageResource(R.drawable.ic_pause)
    }

    private val clickStopOrStartObserver = Observer<Unit> {
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
            tvSong.text = MusicPlaying.listMusics[pos].title
            tvSinger.text = MusicPlaying.listMusics[pos].artistName

            Glide
                .with(requireContext())
                .load(MusicPlaying.listMusics[pos].image)
                .into(binding.imgAlbum)

            musicSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    val dur = MusicPlaying.mediaPlayer?.duration!! * progress / 100
                    if (fromUser) MusicPlaying.mediaPlayer?.seekTo(dur)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) = Unit
                override fun onStopTrackingTouch(p0: SeekBar?) = Unit
            })

            changeSeekBar()

        }
    }

    private fun changeSeekBar() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            while (true) {
                delay(100)
                val percent =
                    MusicPlaying.mediaPlayer?.currentPosition!! * 100 / MusicPlaying.mediaPlayer!!.duration
                binding.musicSeekBar.progress = percent

                binding.tvStartSong.text =
                    formatDuration(MusicPlaying.mediaPlayer!!.currentPosition.toLong())
            }
        }
        binding.tvEndSong.text =
            formatDuration(MusicPlaying.mediaPlayer!!.duration.toLong())
    }

    private fun newChangeSeekBar() {
        runnable = Runnable {
            binding.musicSeekBar.progress = MusicPlaying.mediaPlayer!!.currentPosition
            Handler(Looper.getMainLooper()).postDelayed(runnable, 200)
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 0)
    }
}