package com.lotuss.whattosee.view.adapters

import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.lotuss.whattosee.R
import com.lotuss.whattosee.data.model.MovieModel
import com.lotuss.whattosee.viewmodels.LikedMoviesViewModel
import kotlinx.android.synthetic.main.liked_movie_item.view.*
import android.support.design.widget.Snackbar



class LikedMovieAdapter(private val viewModel: LikedMoviesViewModel) : RecyclerView.Adapter<LikedMovieAdapter.LikedMovieHolder>() {

    private var movies: List<MovieModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikedMovieHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.liked_movie_item, parent, false)
        return LikedMovieHolder(itemView)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: LikedMovieHolder, position: Int) {
        val currentMovie = movies[position]
        holder.movieTitle.text = currentMovie.title
        holder.movieYear.text = currentMovie.year

        holder.deleteButton.setOnClickListener{
            viewModel.deleteFromLiked(currentMovie)

        }
    }

    fun setMovies(movies: List<MovieModel>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class LikedMovieHolder(view: View): RecyclerView.ViewHolder(view) {
        val movieTitle: TextView = view.movie_title
        val movieYear: TextView = view.movie_year
        val deleteButton: ImageButton = view.movie_delete
    }

}