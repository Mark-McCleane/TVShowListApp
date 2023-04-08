package com.example.tvshowapp.MainActivity.repository

import com.example.tvshowapp.MainActivity.model.TVShow

interface TVShowListRepository {

    suspend fun insertTvShow(tvShow:TVShow)

}
