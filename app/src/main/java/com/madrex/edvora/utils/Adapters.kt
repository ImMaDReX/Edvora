package com.madrex.edvora.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImageCircular")
fun ImageView.loadImageCircular(url: String?) {
    Glide.with(this.context).load(url).transform( CircleTransform(this.context)).into(this)
}
@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    Glide.with(this.context).load(url).into(this)
}