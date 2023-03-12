package com.example.tvshowapp.MainActivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import utils.RetrofitService
import utils.TvShowListAdapter
import utils.TvShowViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private var page: Int = 1
    private lateinit var mTvShowViewModel: TvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofitApi = Retrofit.Builder()
            .baseUrl(Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)

        val adapter = TvShowListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tvShowList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        mTvShowViewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)
        mTvShowViewModel.getTvShowListDesc().observe(this, androidx.lifecycle.Observer { newData ->
            adapter.refreshData(newData)
        })

        GlobalScope.launch(Dispatchers.IO) {
            val responses = retrofitApi.getAllTvShows(API_KEY, LANGUAGE, page).await()

            for (result in responses.results) {
                mTvShowViewModel.addTvShow(result)
                Log.e(TAG, "Tv Show successfully added")
            }
        }

        //https://api.themoviedb.org/3/tv/top_rated?api_key=25a8f80ba018b52efb64f05140f6b43c&language=en-US&page=1
    }

    companion object {
        private const val API_KEY: String =
            "25a8f80ba018b52efb64f05140f6b43c" //todo move to local properties for best practice
        private const val BASE_URL: String = "https://api.themoviedb.org"
        private const val LANGUAGE: String = "en-US"
    }
}