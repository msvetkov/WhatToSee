package com.lotuss.whattosee.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.lotuss.whattosee.R
import com.lotuss.whattosee.data.model.MovieModel
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(private val fromFragment: Int) : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var movies: List<MovieModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val currentMovie = movies[position]
        holder.movieTitle.text = currentMovie.title
        holder.movieYear.text = currentMovie.year
    }

    fun setNotes(movies: List<MovieModel>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class MovieHolder(view: View): RecyclerView.ViewHolder(view) {
        val movieImage: ImageView = view.movie_image
        val movieTitle: TextView = view.movie_title
        val movieYear: TextView = view.movie_year
        val likeButton: ImageButton = view.movie_like
    }

}