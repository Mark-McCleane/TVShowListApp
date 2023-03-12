package utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.TVShowListPageResult
import model.TVShowListRepository

class TvShowViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllDataDesc: LiveData<List<TVShowListPageResult>>
    private val repository: TVShowListRepository

    init {
        val tvShowListDAO = TvShowListDatabase.getDatabase(application).tvShowListDao()
        repository = TVShowListRepository(tvShowListDAO)
        readAllDataDesc = repository.readAllDataDesc
    }

    fun addTvShow(tvShowListPageResult: TVShowListPageResult) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTvShow(tvShowListPageResult)
        }
    }

    fun getTvShowListDesc(): LiveData<List<TVShowListPageResult>> {
        return readAllDataDesc
    }
}