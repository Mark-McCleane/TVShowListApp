package com.example.tvshowapp.MainActivity.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TvShowApi {
    private var retrofitApi:RetrofitService

    init {
        retrofitApi = Retrofit.Builder()
            .baseUrl(TvShowConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    fun getApi():RetrofitService{
        return retrofitApi
    }
}
