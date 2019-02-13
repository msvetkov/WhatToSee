package com.lotuss.whattosee.view.adapters

import android.app.Activity
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.lotuss.whattosee.R
import com.lotuss.whattosee.data.model.MovieModel
import com.lotuss.whattosee.view.activities.DetailActivity
import com.lotuss.whattosee.viewmodels.MoviesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.new_movie_item.view.*

class MovieAdapter(private val viewModel: MoviesViewModel, private val isFromNewFragment: Boolean) : RecyclerView.Adapter<MovieAdapter.NewMovieHolder>() {

    private var movies: List<MovieModel> = listOf()
    private var filterList: MutableList<MovieModel> = mutableListOf()

    fun setMovies(movies: List<MovieModel>) {
        this.movies = movies
        filterList.clear()
        filterList.addAll(movies)
        notifyDataSetChanged()
    }

    fun searchMovies(searchText: String) {
        filterList.clear()
        if (searchText.isEmpty()) {
            filterList.addAll(movies)
        }
        else movies.forEach{
            if (it.title.contains(searchText, true)) {
                filterList.add(it)
            }
        }
        notifyDataSetChanged()
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Picasso.get()
            .load(url)
            .into(imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMovieHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_movie_item, parent, false)
        return NewMovieHolder(itemView)
    }

    override fun getItemCount(): Int = filterList.size

    override fun onBindViewHolder(holder: NewMovieHolder, position: Int) = holder.bindView(filterList[holder.adapterPosition])

    inner class NewMovieHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindView(movie: MovieModel) {
            with(itemView) {
                movie_title.text = movie.title
                movie_year.text = movie.year
                loadImage(movie_image, movie.imageUrl)

                val drawable: Int = if (isFromNewFragment) {
                    if (movie.isLiked)
                        R.drawable.ic_like
                    else R.drawable.ic_not_like
                } else R.drawable.ic_delete

                movie_like.setImageDrawable(ContextCompat
                    .getDrawable(movie_like.context, drawable))

                movie_like.setOnClickListener{
                    movie.isLiked = !movie.isLiked
                    viewModel.updateMovieStatus(movie)
                }
                movie_layout.setOnClickListener {
                    val intent = Intent(movie_layout.context, DetailActivity::class.java)
                    intent.putExtra("id", movie.id.toString())
                    Log.d("tagtag", intent.extras!!.getString("id"))
                    (movie_layout.context as Activity).startActivity(intent)
                }
            }
        }
    }

}