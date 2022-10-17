package uz.gita.music_player_io.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.music_player_io.domain.ArtistUseCase
import uz.gita.music_player_io.domain.GetAllMusicsUseCase
import uz.gita.music_player_io.domain.MusicsUseCase
import uz.gita.music_player_io.domain.SplashUseCase
import uz.gita.music_player_io.domain.impl.ArtistUseCaseImpl
import uz.gita.music_player_io.domain.impl.GetAllMusicsUseCaseImpl
import uz.gita.music_player_io.domain.impl.MusicsUseCaseImpl
import uz.gita.music_player_io.domain.impl.SplashUseCaseImpl

// Created by Jamshid Isoqov an 10/7/2022
@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindLoadMusic(impl: GetAllMusicsUseCaseImpl): GetAllMusicsUseCase

    @Binds
    fun bindMusicsUseCase(impl: MusicsUseCaseImpl): MusicsUseCase

    @Binds
    fun bindSplashUseCase(impl: SplashUseCaseImpl): SplashUseCase

    @Binds
    fun bindArtistUseCase(impl:ArtistUseCaseImpl):ArtistUseCase


}