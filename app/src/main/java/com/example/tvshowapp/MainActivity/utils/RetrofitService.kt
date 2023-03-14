package com.example.tvshowapp.MainActivity.utils

import com.example.tvshowapp.MainActivity.model.TVShowListPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/3/tv/top_rated")
    fun getAllTvShows(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
    ): Call<TVShowListPage>
}