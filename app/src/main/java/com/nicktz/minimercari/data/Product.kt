package com.nicktz.minimercari.data

import com.google.gson.annotations.SerializedName
import com.nicktz.minimercari.util.Diffable

/**
 * {
    "id": "mmen1",
    "name": "men1",
    "status": "on_sale",
    "num_likes": 91,
    "num_comments": 59,
    "price": 51,
    "photo": "https://dummyimage.com/400x400/000/fff?text=men1"
},
 */

enum class ProductStatus {

    @SerializedName("on_sale")
    OnSale,

    @SerializedName("sold_out")
    SoldOut
}

data class Product(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: ProductStatus,
    @SerializedName("num_likes")
    val likesNum: Int,
    @SerializedName("num_comments")
    val commentsNum: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("photo")
    val photoUrl: String
): Diffable<Product>