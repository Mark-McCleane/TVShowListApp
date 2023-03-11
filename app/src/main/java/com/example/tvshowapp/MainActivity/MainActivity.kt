package com.example.tvshowapp.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tvshowapp.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //https://api.themoviedb.org/3/tv/top_rated?api_key=25a8f80ba018b52efb64f05140f6b43c&language=en-US&page=1
    }
}