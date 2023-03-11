package utils

import model.TVShowListPage
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    @GET("3/tv/top_rated?api_key=25a8f80ba018b52efb64f05140f6b43c&language=en-US&page=1")
    suspend fun getAllTvShows():Response<List<TVShowListPage>>
}