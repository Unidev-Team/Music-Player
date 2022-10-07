package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.presentation.viewmodels.IntroViewModel
import javax.inject.Inject

@HiltViewModel
class IntroViewModelImpl @Inject constructor(): IntroViewModel, ViewModel() {
    override val openMainFlow = MutableStateFlow(Unit)

    override fun openMainClicked() {
        viewModelScope.launch {
            openMainFlow.emit(Unit)

        }
    }
}