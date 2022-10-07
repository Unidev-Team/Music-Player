package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.domain.MusicsUseCase
import uz.gita.music_player_io.domain.SplashUseCase
import uz.gita.music_player_io.presentation.viewmodels.SplashViewModel
import javax.inject.Inject

class SplashViewModelImpl @Inject constructor(
    private val splashUseCase: SplashUseCase,
    private val musicsUseCase: MusicsUseCase
) : SplashViewModel, ViewModel() {

    override val openIntroFlow = MutableStateFlow(Unit)

    override val openMainFlow = MutableStateFlow(Unit)

    init {
        viewModelScope.launch {
            if (splashUseCase.getIsFirst()) {
                delay(1500)
                openIntroFlow.emit(Unit)
            } else {
                musicsUseCase.refreshMusics()
                openMainFlow.emit(Unit)
            }
        }
    }

}