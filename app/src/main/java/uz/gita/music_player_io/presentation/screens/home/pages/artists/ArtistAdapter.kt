package uz.gita.music_player_io.presentation.screens.home.pages.artists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.ArtistData
import uz.gita.music_player_io.databinding.ItemArtistBinding

class ArtistAdapter:ListAdapter<ArtistData,ArtistAdapter.ViewHolder>(ArtistAdapterComparator) {
    inner class ViewHolder(private val binding:ItemArtistBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.apply {
                tvArtistName.text = getItem(absoluteAdapterPosition).artistName
                tvSongsCount.text = getItem(absoluteAdapterPosition).count.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArtistBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_artist, parent, false)
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     holder.bind()
    }
}

    object ArtistAdapterComparator:DiffUtil.ItemCallback<ArtistData>(){
        override fun areItemsTheSame(oldItem: ArtistData, newItem: ArtistData): Boolean {
            return oldItem.artistName == newItem.artistName
        }

        override fun areContentsTheSame(oldItem: ArtistData, newItem: ArtistData): Boolean {
            return oldItem.artistName == newItem.artistName && oldItem.image == newItem.image && oldItem.count == newItem.count
        }

    }