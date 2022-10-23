package uz.gita.music_player_io.presentation.screens.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.data.model.PlaylistData
import uz.gita.music_player_io.databinding.ItemPlaylistBinding
import uz.gita.music_player_io.databinding.ItemSongBinding

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 13:57
 */

class PlaylistAdapter : ListAdapter<PlaylistData, PlaylistAdapter.PlaylistViewHolder>(PlaylistAdapterComparator) {

    private var itemItemClick: ((PlaylistData) -> Unit)? = null

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemItemClick?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            binding.tvPlaylistName.apply {
                text = getItem(absoluteAdapterPosition).name
                isSelected = true
                setSingleLine()
            }
            binding.tvSongsCount.apply {
                isSelected = true
                setSingleLine()
                text = getItem(absoluteAdapterPosition).musicList.size.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            ItemPlaylistBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind()
    }

    fun setItemClickListener(block: (PlaylistData) -> Unit) {
        itemItemClick = block
    }
}

object PlaylistAdapterComparator : DiffUtil.ItemCallback<PlaylistData>() {

    override fun areItemsTheSame(oldItem: PlaylistData, newItem: PlaylistData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PlaylistData, newItem: PlaylistData): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.musicCount == newItem.musicCount && oldItem.musicList == newItem.musicList

    }

}