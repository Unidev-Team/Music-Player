package uz.gita.music_player_io.presentation.screens.intro.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.gita.music_player_io.R
import uz.gita.music_player_io.databinding.ItemIntroViewpagerBinding

class IntroAdapter:RecyclerView.Adapter<IntroAdapter.ViewHolder>() {
    private val listIntro = listOf(
        Pair(R.drawable.intro1,"User friendly mp3 music player for your device"),
        Pair(R.drawable.intro2,"User friendly mp3 music player for your device2"),
        Pair(R.drawable.intro3,"User friendly mp3 music player for your device3")
    )
    inner class ViewHolder(private val binding:ItemIntroViewpagerBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(){
            val data = listIntro[absoluteAdapterPosition]
            binding.imgIntro.setImageResource(data.first)
            binding.textIntroDescription.text = data.second
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
       val view =  ItemIntroViewpagerBinding.bind(LayoutInflater.from(parent.context).inflate(R.layout.item_intro_viewpager,parent,false))
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = 3
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()
}