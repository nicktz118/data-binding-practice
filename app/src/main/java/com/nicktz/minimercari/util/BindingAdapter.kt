package com.nicktz.minimercari.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import com.nicktz.minimercari.R
import com.nicktz.minimercari.data.ProductStatus


object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImage(view: ImageView, url: String) {
        if (url.isNotEmpty()) {
            val options = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.icon_launcher)
                .error(R.drawable.icon_launcher)
                .priority(Priority.HIGH)

            Glide.with(view.context)
                .load(url)
                .apply(options)
                .into(view)
        } else {
            view.setImageResource(R.drawable.ic_launcher_foreground)
        }
    }

    @JvmStatic
    @BindingAdapter("soldOutLabelVisibility")
    fun setSoldOutLabelVisibility(view: ImageView, status: ProductStatus) {
        view.visibility = if (status == ProductStatus.OnSale) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }
}