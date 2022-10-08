package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov an 10/8/2022
interface SplashViewModel {

    val openIntroFlow: SharedFlow<Unit>

    val openMainFlow: SharedFlow<Unit>

}