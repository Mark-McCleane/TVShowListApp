package com.example.tvshowapp.MainActivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.tvshowapp.MainActivity.model.TVShow

class FakeTvShowListRepository : TVShowListRepository {
    val tvShows = mutableListOf<TVShow>()

    private val observableTvShows= MutableLiveData<List<TVShow>>(tvShows)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    fun refreshLiveData(){
        observableTvShows.postValue(tvShows)
    }

    override suspend fun insertTvShow(tvShow: TVShow) {
        tvShows.add(tvShow)
        refreshLiveData()
    }
}