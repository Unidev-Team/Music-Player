package uz.gita.music_player_io.presentation.screens.playlist.detail.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.data.PlayListWithMusics
import uz.gita.music_player_io.databinding.ItemSelectableSongBinding

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 13:57
 */

class PlaylistAddSongAdapter :
    ListAdapter<PlayListWithMusics, PlaylistAddSongAdapter.PlaylistViewHolder>(
        PlaylistAddSongAdapterComparator
    ) {

    private var itemItemClick: ((PlayListWithMusics) -> Unit)? = null

    inner class PlaylistViewHolder(private val binding: ItemSelectableSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnAdd.setOnClickListener {
                itemItemClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            binding.apply {
                val data = getItem(absoluteAdapterPosition)
                val musicData = data.musicData
                tvSongName.text = musicData.title
                tvArtistName.text = musicData.artistName

                Glide
                    .with(root.context)
                    .load(musicData.image)
                    .placeholder(R.drawable.artist)
                    .into(ivArtist)

                if (data.isInPlaylist) {
                    binding.btnAdd.setImageResource(R.drawable.ic_check)
                } else {
                    binding.btnAdd.setImageResource(R.drawable.ic_add)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemSelectableSongBinding.bind(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_selectable_song, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind()
    }

    fun setIconClickListener(block: (PlayListWithMusics) -> Unit) {
        itemItemClick = block
    }
}

object PlaylistAddSongAdapterComparator : DiffUtil.ItemCallback<PlayListWithMusics>() {
    override fun areItemsTheSame(
        oldItem: PlayListWithMusics,
        newItem: PlayListWithMusics
    ): Boolean = oldItem.musicData.id == newItem.musicData.id

    override fun areContentsTheSame(
        oldItem: PlayListWithMusics,
        newItem: PlayListWithMusics
    ): Boolean =
        oldItem.isInPlaylist == newItem.isInPlaylist &&
                oldItem.musicData.title == newItem.musicData.title&&
                oldItem.musicData.artistName==newItem.musicData.artistName


}