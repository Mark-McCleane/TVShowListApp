package model

import androidx.lifecycle.LiveData
import utils.TvShowListDAO

class TVShowListRepository(private val dao: TvShowListDAO) {

    val readAllDataDesc: LiveData<List<TVShowListPageResult>> = dao.readAllTvShowsOrderedDescending()

    suspend fun addTvShow(newTvShow: TVShowListPageResult){
        dao.addTvShow(newTvShow)
    }

//    fun getTvShowListDesc(): LiveData<List<TVShowListPageResult>> {
//        return dao.readAllTvShowsOrderedDescending()
//    }


}