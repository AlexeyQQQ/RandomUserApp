package com.example.randomuserapp.core

import android.widget.ImageView
import com.example.randomuserapp.R
import com.squareup.picasso.Picasso

interface PicEngine {

    fun show(imageView: ImageView, url: String)

    class Base : PicEngine {

        override fun show(imageView: ImageView, url: String) {
            Picasso.get()
                .load(url)
                .into(imageView)
        }
    }

    class Mock : PicEngine {

        override fun show(imageView: ImageView, url: String) {
            imageView.setImageResource(R.mipmap.ic_launcher)
        }
    }
}