package com.example.tvshowapp.MainActivity.utils

class TvShowConstants {
    companion object {
        const val API_KEY: String =
            "25a8f80ba018b52efb64f05140f6b43c" //usually would move to local properties
                                                // for best practice to avoid reverse engineering
        const val BASE_URL: String = "https://api.themoviedb.org"
        const val LANGUAGE: String = "en-US"
        const val SORTINGSHAREPREF: String = "sorting_share_pref"
        const val SORTING: String = "sorting"
    }
}