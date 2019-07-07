package com.nicktz.minimercari.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductTab(
    @SerializedName("name")
    val name: String,
    @SerializedName("data")
    val url: String
): Parcelable