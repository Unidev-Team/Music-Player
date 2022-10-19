package uz.gita.music_player_io.presentation.screens.playlist.detail.add

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.databinding.ItemSelectableSongBinding

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 13:57
 */

class PlaylistAddSongAdapter :
    ListAdapter<MusicData, PlaylistAddSongAdapter.PlaylistViewHolder>(PlaylistAddSongAdapterComparator) {

    private var itemItemClick: ((MusicData) -> Unit)? = null

    inner class PlaylistViewHolder(private val binding: ItemSelectableSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemItemClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            binding.apply {
                tvSongName.text = getItem(absoluteAdapterPosition).title
                tvArtistName.text = getItem(absoluteAdapterPosition).artistName

                Glide
                    .with(root.context)
                    .load(getItem(absoluteAdapterPosition).image)
                    .placeholder(R.drawable.artist)
                    .into(ivArtist)
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

    fun setIconClickListener(block: (MusicData) -> Unit) {
        itemItemClick = block
    }
}

object PlaylistAddSongAdapterComparator : DiffUtil.ItemCallback<MusicData>() {

    override fun areItemsTheSame(oldItem: MusicData, newItem: MusicData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MusicData, newItem: MusicData): Boolean {
        return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.artistName == newItem.artistName && oldItem.album == newItem.album

    }
}