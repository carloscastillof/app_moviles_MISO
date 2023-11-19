package com.example.vynilos.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.R
import com.example.vynilos.databinding.ActivityDetailAlbumBinding
import com.example.vynilos.models.Track

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackHolder>() {
    private var tracks: List<Track>? = null

    override fun onBindViewHolder(holder: TrackHolder, position: Int) {
        val item = tracks?.get(position)
        holder.bind(item!!)
    }

    fun setTracks(tracksList: List<Track>) {
        this.tracks = tracksList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TrackHolder(layoutInflater.inflate(R.layout.activity_detail_album, parent, false))
    }

    override fun getItemCount(): Int {
        return tracks?.size ?: 0
    }

    class TrackHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ActivityDetailAlbumBinding.bind(view.findViewById(R.id.cardTracks))

        fun bind(track: Track) {
            binding.tvTrackName.text = track.name
            binding.tvTrackDuration.text = track.duration


        }
    }
}