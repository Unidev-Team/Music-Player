package uz.gita.music_player_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.music_player_io.domain.MusicsUseCase
import uz.gita.music_player_io.domain.SplashUseCase
import uz.gita.music_player_io.presentation.viewmodels.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val splashUseCase: SplashUseCase,
    private val musicsUseCase: MusicsUseCase
) : SplashViewModel, ViewModel() {

    override val openIntroFlow = MutableSharedFlow<Unit>()

    override val openMainFlow = MutableSharedFlow<Unit>()

    override fun openScreens() {
        viewModelScope.launch {
            musicsUseCase.refreshMusics()
            delay(1000)
            if (splashUseCase.getIsFirst()) {
                openIntroFlow.emit(Unit)
            } else {
                openMainFlow.emit(Unit)
            }
        }
    }


}