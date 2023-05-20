package com.blackstoneeit.ncgrtask.presentation.base

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.blackstoneeit.ncgrtask.R
import com.bumptech.glide.Glide


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.no_image_icon)
        .into(view)
}