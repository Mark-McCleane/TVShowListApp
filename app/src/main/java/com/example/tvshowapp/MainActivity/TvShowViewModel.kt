package com.example.tvshowapp.MainActivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.tvshowapp.MainActivity.model.TVShow
import com.example.tvshowapp.MainActivity.model.TVShowListRepository
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import com.example.tvshowapp.MainActivity.utils.RetrofitService
import com.example.tvshowapp.MainActivity.utils.TvShowConstants
import com.example.tvshowapp.MainActivity.utils.TvShowListDatabase

class TvShowViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllDataDesc: LiveData<List<TVShow>>
    private val readAllDataAsc: LiveData<List<TVShow>>
    private val repository: TVShowListRepository

    init {
        val tvShowListDAO = TvShowListDatabase.getDatabase(application).tvShowListDao()
        repository = TVShowListRepository(tvShowListDAO)
        readAllDataDesc = repository.readAllDataDesc
        readAllDataAsc = repository.readAllDataAsc
    }

    fun addTvShow(tvShow: TVShow) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTvShow(tvShow)
        }
    }

    fun getTvShowListDesc(): LiveData<List<TVShow>> {
        return readAllDataDesc
    }

    fun getTvShowListAsc(): LiveData<List<TVShow>> {
        return readAllDataAsc
    }

    fun addTvShowsToRoomDb() {
        val page: Int = 1

        val retrofitApi = Retrofit.Builder()
            .baseUrl(TvShowConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val responses = retrofitApi.getAllTvShows(TvShowConstants.API_KEY,
                TvShowConstants.LANGUAGE, page).await()

            for (result in responses.results) {
                addTvShow(result)
            }
        }
    }
}