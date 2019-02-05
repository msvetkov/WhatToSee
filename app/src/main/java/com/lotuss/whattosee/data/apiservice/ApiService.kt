package com.lotuss.whattosee.data.apiservice

import com.lotuss.whattosee.data.model.MovieModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("api-v2/?source=http://hydramovies.com/api-v2/current-Movie-Data.csv")
    fun getNewMovies(): Observable<List<MovieModel>>
}