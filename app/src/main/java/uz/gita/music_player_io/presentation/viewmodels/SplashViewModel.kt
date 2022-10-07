package uz.gita.music_player_io.presentation.viewmodels

import kotlinx.coroutines.flow.Flow

// Created by Jamshid Isoqov an 10/8/2022
interface SplashViewModel {

    val openIntroFlow: Flow<Unit>

    val openMainFlow: Flow<Unit>

}