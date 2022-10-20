package uz.gita.music_player_io.presentation.screens.home.pages.songs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.MusicData
import uz.gita.music_player_io.databinding.ItemSongBinding
import uz.gita.music_player_io.utils.extensions.longToMin

/**
 *  Created by Nurlibay Koshkinbaev on 08/10/2022 13:57
 */

class SongsAdapter : ListAdapter<MusicData, SongsAdapter.SongsViewHolder>(SongsAdapterComparator) {

    private var itemItemClick: ((Int) -> Unit)? = null

    inner class SongsViewHolder(private val binding: ItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemItemClick?.invoke(absoluteAdapterPosition)
            }
        }

        fun bind() {
            binding.tvSongName.apply {
                text = getItem(absoluteAdapterPosition).title
                isSelected = true
                setSingleLine()
            }
            binding.tvSongDescription.apply {
                isSelected = true
                setSingleLine()
                text = getItem(absoluteAdapterPosition).artistName
            }

            binding.tvSongDuration.text = getItem(absoluteAdapterPosition).duration.longToMin()

            Glide
                .with(binding.root.context)
                .load(getItem(absoluteAdapterPosition).image)
                .placeholder(R.drawable.artist)
                .into(binding.ivArtist)
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

    fun setItemClickListener(block: (Int) -> Unit) {
        itemItemClick = block
    }
}

object SongsAdapterComparator : DiffUtil.ItemCallback<MusicData>() {

    override fun areItemsTheSame(oldItem: MusicData, newItem: MusicData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MusicData, newItem: MusicData): Boolean {
        return oldItem.id == newItem.id && oldItem.displayName == newItem.displayName &&
                oldItem.album == newItem.album && oldItem.duration == newItem.duration && oldItem.title == newItem.title && oldItem.path == newItem.path

    }

}