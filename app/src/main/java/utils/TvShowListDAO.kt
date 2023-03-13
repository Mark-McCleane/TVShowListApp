package utils

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import model.TVShow

@Dao
interface TvShowListDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTvShow(newTvShow: TVShow)

    @Query("SELECT * FROM tvShowListTable t ORDER BY t.name ASC")
    fun readAllTvShowsOrderedAscending(): LiveData<List<TVShow>>

    @Query("SELECT * FROM tvShowListTable t ORDER BY t.name Desc")
    fun readAllTvShowsOrderedDescending(): LiveData<List<TVShow>>
}