package utils

import model.TVShowListPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/3/tv/top_rated")
    fun getAllTvShows(
        @Query("api_key") apiKey:String,
        @Query("language") language:String,
        @Query("page") page:Int
    ): Call<TVShowListPage>
}
//    https://api.themoviedb.org/3/tv/top_rated?api_key=25a8f80ba018b52efb64f05140f6b43c&language=en-US&page=1