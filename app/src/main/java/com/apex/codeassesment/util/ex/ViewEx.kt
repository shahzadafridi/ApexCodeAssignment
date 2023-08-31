package com.apex.codeassesment.util.ex

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.apex.codeassesment.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

fun ImageView.load(
    url: Any?,
    onLoadingFinished: (String?) -> Unit = {},
    @DrawableRes placeholder: Int = R.mipmap.ic_launcher,
    circular: Boolean = false
) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished(e?.message)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished(null)
            return false
        }

    }
    val request = Glide.with(this)
        .load(url)
        .apply(RequestOptions.placeholderOf(placeholder))
        .listener(listener)
    if (circular) { request.circleCrop() }
    request.into(this)
}


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}