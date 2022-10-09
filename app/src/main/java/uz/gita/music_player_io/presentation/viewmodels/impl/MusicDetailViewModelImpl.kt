package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.music_player_io.presentation.viewmodels.MusicDetailViewModel
import javax.inject.Inject

@HiltViewModel
class MusicDetailViewModelImpl @Inject constructor() : MusicDetailViewModel, ViewModel() {

    override val clickBack: MutableLiveData<Unit> = MutableLiveData()

    override val clickPrevious: MutableLiveData<Unit> = MutableLiveData()

    override val clickNextSong: MutableLiveData<Unit> = MutableLiveData()

    override val clickStopOrStart: MutableLiveData<Unit> = MutableLiveData()

    override fun setClickBack() {
        viewModelScope.launch {
            clickBack.value = Unit
        }
    }

    override fun setClickPrevious() {
        viewModelScope.launch {
            clickPrevious.value = Unit
        }
    }

    override fun setClickNextSong() {
        viewModelScope.launch {
            clickNextSong.value = Unit
        }
    }

    override fun setClickStopOrStart() {
        viewModelScope.launch {
            clickStopOrStart.value = Unit
        }
    }
}