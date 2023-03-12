package utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.TVShowListPageResult

@Dao
interface TvShowListDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTvShow(newTvShow: TVShowListPageResult)

    @Query("SELECT * FROM tvShowListTable t ORDER BY t.name ASC")
    fun readAllTvShowsOrderedAscending():LiveData<List<TVShowListPageResult>>

    @Query("SELECT * FROM tvShowListTable t ORDER BY t.name Desc")
    fun readAllTvShowsOrderedDescending():LiveData<List<TVShowListPageResult>>

}