package com.lotuss.whattosee.util

import android.widget.ImageView

import com.squareup.picasso.Picasso


class LoadImage {
    companion object {
        fun loadImage(imageView: ImageView, url: String) {
            Picasso.get()
                .load(url)
                .resize(430,400)
                .centerCrop()
                .into(imageView)
        }
    }
}