package uz.gita.music_player_io.presentation.screens.home.pages.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.databinding.ItemSongBinding

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 13:57
 */

class SongsAdapter : RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {

    var musicList = mutableListOf<MusicData>()

    inner class SongsViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val data = musicList[absoluteAdapterPosition]
            binding.tvSongName.text = data.displayName
            binding.tvSongDescription.text = data.artistName
            binding.tvSongDuration.text = data.duration.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        return SongsViewHolder(
            ItemSongBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = musicList.size

}