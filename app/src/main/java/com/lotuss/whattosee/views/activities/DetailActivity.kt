package com.lotuss.whattosee.views.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.lotuss.whattosee.R
import com.lotuss.whattosee.viewmodel.MoviesViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var id: Long = 1
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar!!.title = "Movie info"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        if (bundle != null) id = intent.extras!!.getString("id")!!.toLong()

        viewModel = ViewModelProviders.of(this).get(MoviesViewModel(this.application)::class.java)
        viewModel.getMovieById(id).observe(this, Observer {
            if (it != null) {
                detail_title.text = it.title
                val info = it.year + "     " + it.genres.replace("|", ", ") + "     IMDb Rating: " + it.rating
                detail_info.text = info
                detail_description.text = it.description
                loadImage(detail_image, it.imageUrl)

                if (!it.isLiked)
                    add_button.setText(R.string.add_to_watchlist)
                else add_button.setText(R.string.remove_from_watchlist)

                add_button.setOnClickListener {_ ->
                    it.isLiked = !it.isLiked
                    if (it.isLiked)
                        showMessage(R.string.added_to_watchlist)
                    else showMessage(R.string.removed_from_watchlist)
                    viewModel.updateMovieStatus(it)
                }
            }

        })
    }

    private fun showMessage(msg: Int) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun loadImage(imageView: ImageView, url: String) {
        Picasso.get()
            .load(url)
            .into(imageView)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
