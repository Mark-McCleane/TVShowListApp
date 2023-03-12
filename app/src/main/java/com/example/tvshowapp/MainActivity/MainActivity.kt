package com.example.tvshowapp.MainActivity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tvshowapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory
import utils.RetrofitService

class MainActivity : AppCompatActivity() {


    private val TAG = "MainActivity"
    private var page: Int = 1
    private lateinit var textView: TextView
    private lateinit var arrayList: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var retrofitApi = Retrofit.Builder()
            .baseUrl(Companion.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
        textView = findViewById(R.id.sampleTextView)
        arrayList = ArrayList();

        GlobalScope.launch(Dispatchers.IO) {
            val responses = retrofitApi.getAllTvShows(API_KEY, LANGUAGE, page).await()

            Log.e(TAG, "Page = ${responses.page}")
            for (result in responses.results) {
                arrayList.add(result.name)
            }

            Log.e(TAG, arrayList[0]); //PROOF DATA IS PULLLING FROM API
        }

        //https://api.themoviedb.org/3/tv/top_rated?api_key=25a8f80ba018b52efb64f05140f6b43c&language=en-US&page=1
    }

    companion object {
        private const val API_KEY: String = "25a8f80ba018b52efb64f05140f6b43c" //todo move to local properties for best practice
        private const val BASE_URL: String = "https://api.themoviedb.org"
        private const val LANGUAGE: String = "en-US"
    }
}