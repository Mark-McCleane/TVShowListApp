package model

import androidx.lifecycle.LiveData
import utils.TvShowListDAO

class TVShowListRepository(private val dao: TvShowListDAO) {

    val readAllDataDesc: LiveData<List<TVShow>> = dao.readAllTvShowsOrderedDescending()
    val readAllDataAsc: LiveData<List<TVShow>> = dao.readAllTvShowsOrderedAscending()

    suspend fun addTvShow(newTvShow: TVShow) {
        dao.addTvShow(newTvShow)
    }
}