package com.example.vynilos.views.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vynilos.R
import com.example.vynilos.databinding.ItemAlbumBinding
import com.example.vynilos.models.Album
import com.example.vynilos.views.AlbumsDetailActivity
import com.squareup.picasso.Picasso
import java.text.Normalizer
import java.util.Locale

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {
    private var albums: List<Album>? = null
    private var filteredAlbums: List<Album>? = null

    init {
        filteredAlbums = albums
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        val item = filteredAlbums?.get(position)
        holder.bind(item!!)
    }

    fun setAlbums(albumsList: List<Album>) {
        albums = albumsList
        filteredAlbums = albumsList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        val normalizedQuery = removeAccents(query)
        filteredAlbums = if (normalizedQuery.isEmpty()) {
            albums
        } else {
            albums?.filter { album ->
                val normalizedName = removeAccents(album.name).lowercase(Locale.getDefault())
                val normalizedDescription = removeAccents(album.description).lowercase(Locale.getDefault())
                normalizedName.contains(normalizedQuery.lowercase(Locale.getDefault())) || normalizedDescription.contains(
                    normalizedQuery.lowercase(Locale.getDefault())
                )
            }
        }
        notifyDataSetChanged()
    }

    private fun removeAccents(text: String): String {
        val normalizedText = Normalizer.normalize(text, Normalizer.Form.NFD)
        return normalizedText.replace("[\\p{InCombiningDiacriticalMarks}]".toRegex(), "")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlbumHolder(layoutInflater.inflate(R.layout.item_album, parent, false))
    }

    override fun getItemCount(): Int {
        return filteredAlbums?.size ?: 0
    }

    class AlbumHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemAlbumBinding.bind(view)

        fun bind(album:Album){

            binding.tvName.text = album.name
            binding.tvDescription.text = album.description

            val releaseDate = album.releaseDate
            val year = releaseDate.substring(0, 4)
            val releaseGenreText = "$year - ${album.genre}"

            binding.tvReleaseDate.text = releaseGenreText

            Picasso.get().load(album.cover).into(binding.ivCover)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, AlbumsDetailActivity::class.java)
                intent.putExtra("albumId", album.id.toString())
                itemView.context.startActivity(intent)
            }
        }
    }
}