package com.example.tvshowapp.MainActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.androiddevs.shoppinglisttestingyt.other.Event
import com.example.tvshowapp.MainActivity.utils.Resource
import com.example.tvshowapp.MainActivity.model.TVShow
import com.example.tvshowapp.MainActivity.repository.DefaultTVShowListRepository
import com.example.tvshowapp.MainActivity.utils.TvShowApi
import com.example.tvshowapp.MainActivity.utils.TvShowConstants
import com.example.tvshowapp.MainActivity.utils.TvShowListDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.await

class TvShowViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllDataDesc: LiveData<List<TVShow>>
    private val readAllDataAsc: LiveData<List<TVShow>>
    private val repository: DefaultTVShowListRepository

    private val _insertTvShowStatus = MutableLiveData<Event<Resource<TVShow>>>()
    val insertTvShowStatus: LiveData<Event<Resource<TVShow>>> = _insertTvShowStatus


    init {
        val tvShowListDAO = TvShowListDatabase.getDatabase(application).tvShowListDao()
        repository = DefaultTVShowListRepository(tvShowListDAO)
        readAllDataDesc = repository.readAllDataDesc
        readAllDataAsc = repository.readAllDataAsc
    }
/**
 * if no tv show name or overview is not found we do not add
 * but any other error should be allowed!
 * */
    fun addTvShow(tvShow: TVShow) {
        if (tvShow.name.isEmpty()) {
            _insertTvShowStatus.postValue(
                Event(Resource.error("title not found", null)))
            return
        }

        if (tvShow.overview.isEmpty()) {
            _insertTvShowStatus.postValue(
                Event(Resource.error("overview not found", null)))
            return
        }

        if (tvShow.posterPath.isEmpty()) {
            _insertTvShowStatus.postValue(
                Event(Resource.error("poster path is not found", null)))
        }

        if (tvShow.firstAirDate.isEmpty()) {
            _insertTvShowStatus.postValue(
                Event(Resource.error("air date not found", null)))
        }

        viewModelScope.launch(Dispatchers.IO) {
            repository.addTvShow(tvShow)
        }
        _insertTvShowStatus.postValue(
            Event(Resource.success(tvShow)))
    }

    fun getTvShowListDesc(): LiveData<List<TVShow>> {
        return readAllDataDesc
    }

    fun getTvShowListAsc(): LiveData<List<TVShow>> {
        return readAllDataAsc
    }

    fun addTvShowsToRoomDb() {
        val page: Int = 1

        val retrofitTvShowApi = TvShowApi().getApi()

        viewModelScope.launch(Dispatchers.IO) {
            val responses = retrofitTvShowApi.getAllTvShows(TvShowConstants.API_KEY,
                TvShowConstants.LANGUAGE, page).await()

            for (result in responses.results) {
                addTvShow(result)
            }
        }
    }
}