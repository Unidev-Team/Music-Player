package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow

interface IntroViewModel {
    val openMainFlow: Flow<Unit>

    fun openMainClicked()
}