package com.inerviewapp.utils.bindingAdapter

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.textfield.TextInputLayout
import com.inerviewapp.R

@BindingAdapter("android:imageUrl")
fun imageUrl(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context).load(it).apply(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        ).into(view)
    }
}


@BindingAdapter("android:bindError")
fun bindError(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
}

@BindingAdapter("android:bindVisibility")
fun bindVisibility(view: View, isVisible: Boolean) {
    view.isVisible = isVisible
}

