package uz.gita.music_player_io.presentation.screens.home.pages

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.music_player_io.presentation.screens.home.pages.folder.FolderPage
import uz.gita.music_player_io.presentation.screens.home.pages.artists.ArtistPage
import uz.gita.music_player_io.presentation.screens.home.pages.songs.SongsPage

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 12:31
 */

class HomeViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int) = when (position) {
        0 -> SongsPage()
        1 -> ArtistPage()
        else -> FolderPage()
    }
}