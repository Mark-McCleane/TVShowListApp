package utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowapp.R
import com.squareup.picasso.Picasso
import model.TVShow

class TvShowListAdapter : RecyclerView.Adapter<TvShowListAdapter.TvShowViewHolder>() {
    private var tvShowList = emptyList<TVShow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        return TvShowViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tv_show, parent, false))
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = tvShowList[position]
        val imageUrl:String = "https://image.tmdb.org/t/p/w500/${tvShow.posterPath}"
        Picasso.get().load(imageUrl).into(holder.tvShowPoster)
        holder.tvShowTitle.setText(tvShow.name)
        holder.tvShowReleaseDate.setText(tvShow.firstAirDate)
        holder.tvShowOverview.setText(tvShow.overview)
    }

    override fun getItemCount(): Int {
        return tvShowList.size
    }

    fun refreshData(data: List<TVShow>){
        this.tvShowList = data
        notifyDataSetChanged()
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvShowPoster:ImageView = itemView.findViewById(R.id.image_tvshow_poster);
        val tvShowTitle:TextView = itemView.findViewById(R.id.text_tvshow_title)
        val tvShowReleaseDate:TextView = itemView.findViewById(R.id.text_first_air_date)
        val tvShowOverview:TextView = itemView.findViewById(R.id.text_overview)
    }

}