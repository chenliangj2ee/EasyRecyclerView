package com.ktl.mvvm

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageLoader{
    @BindingAdapter("load")
    @JvmStatic
    open fun loadImage(
        imageView: ImageView?,
        url: String?
    ) {
        if(imageView==null)
            return
        Glide.with(imageView!!.context)
            .load(url)
            .into(imageView)
    }
}