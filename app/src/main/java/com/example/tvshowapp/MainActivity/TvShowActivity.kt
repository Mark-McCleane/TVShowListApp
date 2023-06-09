package com.example.tvshowapp.MainActivity

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tvshowapp.MainActivity.utils.TvShowConstants
import com.example.tvshowapp.MainActivity.utils.TvShowListAdapter
import com.example.tvshowapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class TvShowActivity : AppCompatActivity() {
    private lateinit var mTvShowViewModel: TvShowViewModel
    private var mSortingSharedPref: SharedPreferences? = null
    private var mSortedAsc = true
    private lateinit var mFloatingActionButton: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TvShowListAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mSortingSharedPref =
            getSharedPreferences(TvShowConstants.SORTINGSHAREPREF, Context.MODE_PRIVATE)
        mTvShowViewModel = ViewModelProvider(this)[TvShowViewModel::class.java]

        if (mSortingSharedPref != null && mSortedAsc !=
            mSortingSharedPref!!.getBoolean(TvShowConstants.SORTING, false)
        ) {
            mSortedAsc = mSortingSharedPref!!.getBoolean(TvShowConstants.SORTING, false)
        }

        adapter = TvShowListAdapter()
        recyclerView = binding.recyclerViewTvShowList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mFloatingActionButton = binding.floatingActionButton
        mFloatingActionButton.setOnClickListener {
            mSortedAsc = !mSortedAsc
            val editor = mSortingSharedPref!!.edit()
            editor.putBoolean(TvShowConstants.SORTING, mSortedAsc)
            editor.apply()

            adapter.reverseData()
        }

        addTvShowsForCaching()
        addObservers()
    }

    private fun addObservers() {
        if (mSortedAsc) {
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

    fun addTvShowsForCaching(){
        if (isOnline(applicationContext)) {
            mTvShowViewModel.addTvShowsToRoomDb()
        } else {
            val noInternetConnectionSnackbar =
                Snackbar.make(recyclerView, "No Internet Connection", Snackbar.LENGTH_INDEFINITE)
            noInternetConnectionSnackbar.setAction("Dismiss",
                View.OnClickListener { noInternetConnectionSnackbar.dismiss() })
            noInternetConnectionSnackbar.show()
        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}