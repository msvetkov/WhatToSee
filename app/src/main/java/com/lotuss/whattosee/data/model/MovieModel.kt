package com.lotuss.whattosee.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieModel (
    @SerializedName("Title")val title: String,
    @SerializedName("movie_year")val year: String,
    @SerializedName("Categories")val genres: String,
    @SerializedName("summary")var description: String,
    @SerializedName("ImageURL")val imageUrl: String,
    @SerializedName("imdb_rating")val rating: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    var isLiked: Boolean = false
}