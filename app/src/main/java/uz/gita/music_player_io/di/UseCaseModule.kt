package uz.gita.music_player_io.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.music_player_io.domain.*
import uz.gita.music_player_io.domain.impl.*

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
    fun bindArtistUseCase(impl: ArtistUseCaseImpl): ArtistUseCase

    @Binds
    fun bindAddPlaylistUseCase(impl: PlaylistUseCaseImpl): PlaylistUseCase

    @Binds
    fun bindsFoldersUseCase(impl: FolderUseCaseImpl): FolderUseCase
}