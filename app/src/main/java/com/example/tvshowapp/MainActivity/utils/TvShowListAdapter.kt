package com.example.tvshowapp.MainActivity.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowapp.R
import com.squareup.picasso.Picasso
import com.example.tvshowapp.MainActivity.model.TVShow
import com.example.tvshowapp.databinding.ItemTvShowBinding

class TvShowListAdapter : RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder>() {
    private var tvShowList = emptyList<TVShow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val binding = ItemTvShowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShowList[position]
        val imageUrl: String = "https://image.tmdb.org/t/p/w500/${tvShow.posterPath}"
        Picasso.get().load(imageUrl).into(holder.tvShowPoster)
        holder.tvShowTitle.text = tvShow.name
        holder.tvShowReleaseDate.text = tvShow.firstAirDate
        holder.tvShowOverview.text = tvShow.overview
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    fun refreshData(data: List<TVShow>) {
        this.tvShowList = data
        notifyDataSetChanged()
    }

    class TvShowViewHolder(val binding: ItemTvShowBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvShowPoster: ImageView = binding.imageTvshowPoster;
        val tvShowTitle: TextView = binding.textTvshowTitle
        val tvShowReleaseDate: TextView = binding.textFirstAirDate
        val tvShowOverview: TextView = binding.textOverview
    }
}