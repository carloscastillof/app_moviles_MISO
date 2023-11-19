package com.example.vynilos.views.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.databinding.ActivityMusiciansBinding
import com.example.vynilos.databinding.ItemMusicianBinding
import com.example.vynilos.models.Artist
import com.squareup.picasso.Picasso

class MusicianAdapter(private var musicians:List<Artist>):RecyclerView.Adapter<MusicianAdapter.MusicianHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicianAdapter.MusicianHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMusicianBinding.inflate(inflater, parent, false)
        return MusicianHolder(binding)
    }

    class MusicianHolder(private val binding: ItemMusicianBinding):RecyclerView.ViewHolder(binding.root) {
        public fun bind(musician:Artist){
            binding.Name.text = musician.name
            binding.tvGenre.text = musician.description
            Picasso.get().load(musician.image).into(binding.ivImage)
            Log.i("MusicianHolder","Se vinculo el musico ${musician.name}")
        }
    }

    override fun onBindViewHolder(holder: MusicianAdapter.MusicianHolder, position: Int) {
       val musician = musicians[position]
        holder.bind(musician)
        Log.i("OnBindViewHolder","binding Musician ${musician.name} in pos $position")
    }

    override fun getItemCount(): Int {
        return musicians.size
    }

    public fun setArtists(musicians:List<Artist>){
        this.musicians = musicians
        notifyDataSetChanged()
    }

}