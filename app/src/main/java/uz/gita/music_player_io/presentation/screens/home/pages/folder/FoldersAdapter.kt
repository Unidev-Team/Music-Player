package uz.gita.music_player_io.presentation.screens.home.pages.folder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.music_player_io.R
import uz.gita.music_player_io.data.model.FolderData
import uz.gita.music_player_io.databinding.ListItemFoldersBinding

// Created by Jamshid Isoqov an 10/21/2022
class FoldersAdapter : ListAdapter<FolderData, FoldersAdapter.ViewHolder>(itemFolderCallback) {

    private var itemClickListener: ((FolderData) -> Unit)? = null

    fun setItemClickListener(block: (FolderData) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(val binding: ListItemFoldersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind() {
            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvFolderName.text = data.path.substring(data.path.lastIndexOf('/'))
                tvFolderMusicCount.text = "${data.count} songs"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ListItemFoldersBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_folders, parent, false)
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}

private val itemFolderCallback = object : DiffUtil.ItemCallback<FolderData>() {
    override fun areItemsTheSame(oldItem: FolderData, newItem: FolderData): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: FolderData, newItem: FolderData): Boolean =
        oldItem.folderName == newItem.folderName &&
                oldItem.path == newItem.path &&
                oldItem.image == newItem.image &&
                oldItem.count == newItem.count

}