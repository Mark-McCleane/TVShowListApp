package com.example.tvshowapp.MainActivity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import utils.TvShowConstants
import utils.TvShowListAdapter

class TvShowActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var mTvShowViewModel: TvShowViewModel
    private var sortingSharedPref: SharedPreferences? = null
    private var sortedAsc = true
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sortingSharedPref =
            getSharedPreferences(TvShowConstants.SORTINGSHAREPREF, Context.MODE_PRIVATE)
        mTvShowViewModel = ViewModelProvider(this).get(TvShowViewModel::class.java)

        if (sortingSharedPref != null && sortedAsc !=
            sortingSharedPref!!.getBoolean(TvShowConstants.SORTING, false)
        ) {
            sortedAsc = sortingSharedPref!!.getBoolean(TvShowConstants.SORTING, false)
        }

        val adapter = TvShowListAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tvShowList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        floatingActionButton = findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            sortedAsc = !sortedAsc
            val editor = sortingSharedPref!!.edit()
            editor.putBoolean(TvShowConstants.SORTING, sortedAsc)
            editor.apply()
            val restartIntent: Intent = intent
            finish()
            startActivity(restartIntent)
        }
        mTvShowViewModel.addTvShowsToRoomDb()

        if (sortedAsc) {
            mTvShowViewModel.getTvShowListAsc()
                .observe(this, androidx.lifecycle.Observer { data ->
                    adapter.refreshData(data)
                })
        } else {
            mTvShowViewModel.getTvShowListDesc()
                .observe(this, androidx.lifecycle.Observer { newData ->
                    adapter.refreshData(newData)
                })
        }
    }
}