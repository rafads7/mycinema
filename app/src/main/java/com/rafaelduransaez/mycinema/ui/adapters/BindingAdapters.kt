package com.rafaelduransaez.mycinema.ui.adapters

import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.rafaelduransaez.mycinema.extensions.gone
import com.rafaelduransaez.mycinema.extensions.loadUrl
import com.rafaelduransaez.mycinema.extensions.visible

@BindingAdapter("compat_url")
fun AppCompatImageView.bindUrl(url: String?) {
    if (url != null) loadUrl("https://image.tmdb.org/t/p/w185/$url")
}

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null) loadUrl("https://image.tmdb.org/t/p/w185/$url")
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) {
    if (visible == true) visible() else gone()
}